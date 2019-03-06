package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.data.protocal.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.LoginPresenter
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Author：Pengllrn
 * Date: 2019/3/4
 * 登录界面
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(),LoginView,View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.enable({isBtnEnable()},mMobileEt,mPwdEt)
        mLoginBtn.setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)

//        mHeaderBar.mRightTv.setOnClickListener(this) //错误，无法访问到HeaderBar里面的资源
        mHeaderBar.getRightView().setOnClickListener(this)
    }

    fun isBtnEnable():Boolean{
        return mMobileEt.text.isNotEmpty() && mPwdEt.text.isNotEmpty()
    }

    override fun injectComponent() {//如果使用到了Dagger，需要在这里绑定Component
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)//存储用户信息
        finish()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mLoginBtn ->{
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString())
            }
            R.id.mRightTv ->{
                startActivity<RegisterActivity>()
            }

            R.id.mForgetPwdTv ->{
                startActivity<ForgetPwdActivity>()
            }
        }
    }

//    override fun onBackPressed() {
//        AppManager.INSTANCE.exitApp(this)
//    }
}