package com.kotlin.user.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import com.bigkoo.alertview.AlertView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.R
import com.kotlin.user.data.protocal.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.UserInfoPresenter
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.utils.UserPrefsUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File


/**
 * Author：pengllrn
 * Time: 2019/3/4 19:45
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView,
        View.OnClickListener, TakePhoto.TakeResultListener, InvokeListener {
    override fun invoke(invokeParam: InvokeParam): TPermissionType {
        val type:TPermissionType= PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod())
        if(TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam
        }
        return type
    }

    private lateinit var mTakePhoto: TakePhoto

    //临时文件,拍照的图片临时保存的位置
    private lateinit var mTempFile: File

    private lateinit var invokeParam: InvokeParam

    //七牛云
    private val mUploadManager: UploadManager by lazy { UploadManager() }

    //本地图片地址
    private var mLocalFilePath: String? = null
    //远程图片地址
    private var mRemoteFilePath: String? = null

    //用户头像
    private var mUserIcon: String? = null
    //用户名字
    private var mUserName: String? = null
    //用户性别
    private var mUserGender: String? = null
    //用户签名
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kotlin.user.R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mArrowIv.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        //显示头像
        if(mUserIcon != ""){
            mRemoteFilePath = mUserIcon
            GlideUtils.loadUrlImage(this,mUserIcon!!,mUserIconIv)
        }
        //用户名字
        mUserNameEt.setText(mUserName)
        //性别
        if(mUserGender == "0"){
            mGenderMaleRb.isChecked = true
        }else{
            mGenderFemaleRb.isChecked = true
        }
        //签名
        mUserSignEt.setText(mUserSign)
        //手机号码---不可编辑
        mUserMobileTv.text=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)

    }


    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mArrowIv, R.id.mUserIconIv -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                    } else {
                        showAlertView()
                    }
                } else
                    showAlertView()

            }

            R.id.mRightTv ->{
                mPresenter.saveUser(mRemoteFilePath!!,mUserNameEt.text.toString(),
                        if (mGenderMaleRb.isChecked) "0" else "1",
                        mUserSignEt.text.toString())
            }

        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"),
                this, AlertView.Style.ActionSheet) { o, position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),//这样每次拍照或者选择照片都会压缩
                    false)
            when (position) {
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))//没压缩
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }.show()
    }

    //Takephoto成功，即从相册或相机成功拿到图片
    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)//源地址
        Log.d("TakePhoto", result?.image?.compressPath)//压缩后的地址
        //上传头像
        mLocalFilePath = result?.image?.compressPath
        mPresenter.getUploadToken()//拿上传凭证
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            for((i,permision ) in permissions.withIndex()){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    toast("需要您授予权限:${permision[i]}")
                    return
                }
            }
            showAlertView()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"  //文件名
        //判断SD卡是否可用
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //生成了临时文件对象
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        //filesDir是系统获取的
        this.mTempFile = File(filesDir, tempFileName)
    }

    //长传图片完成后七牛云给的回调
    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFilePath, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFilePath = BaseConstant.SERVER_IMAGE_ADDRESS + response?.get("hash")
                Log.d("TakePhoto", mRemoteFilePath)
                //将这个地址传给自己的服务器
                GlideUtils.loadImage(this@UserInfoActivity, mRemoteFilePath!!, mUserIconIv)
            }
        }, null)
    }

    //用户资料服务器保存成功
    override fun onSaveUserInfo(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
        toast("修改成功")
        finish()
    }

}