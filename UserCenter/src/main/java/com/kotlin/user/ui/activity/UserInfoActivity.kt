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
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.DateUtils
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.UserInfoPresenter
import com.kotlin.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import java.io.File


/**
 * Author：pengllrn
 * Time: 2019/3/4 19:45
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView,
        View.OnClickListener , TakePhoto.TakeResultListener, InvokeListener {
    private lateinit var mTakePhoto:TakePhoto

    //临时文件,拍照的图片临时保存的位置
    private lateinit var mTempFile: File

    private lateinit var invokeParam: InvokeParam

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kotlin.user.R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this,this)
        initView()

        mTakePhoto.onCreate(savedInstanceState)
    }

    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mArrowIv.setOnClickListener(this)
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
        when(view.id){
            com.kotlin.user.R.id.mArrowIv, com.kotlin.user.R.id.mUserIconIv ->{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                    }else{
                        showAlertView()
                    }
                }else
                    showAlertView()

            }

        }
    }

    private fun showAlertView(){
        AlertView("选择图片","","取消",null, arrayOf("拍照","相册"),
                this,AlertView.Style.ActionSheet){
            o,position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),//这样每次拍照或者选择照片都会压缩
                    false)
            when(position){
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))//没压缩
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }.show()
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto",result?.image?.originalPath)//源地址
        Log.d("TakePhoto",result?.image?.compressPath)//压缩后的地址
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto",msg)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }

    override fun invoke(invokeParam: InvokeParam): TPermissionType? {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }

    fun createTempFile(){
        val tempFileName = "${DateUtils.curTime}.png"  //文件名
        //判断SD卡是否可用
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            //生成了临时文件对象
            this.mTempFile = File(Environment.getExternalStorageDirectory(),tempFileName)
            return
        }

        //filesDir是系统获取的
        this.mTempFile = File(filesDir,tempFileName)
    }


}