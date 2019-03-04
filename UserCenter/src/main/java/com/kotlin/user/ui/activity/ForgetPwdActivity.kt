package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.widgets.VerifyButton
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ForgetPwdPresenter
import com.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.toast

/**
 * Author：pengllrn
 * Time: 2019/3/4 15:56
 */
class ForgetPwdActivity:BaseMvpActivity<ForgetPwdPresenter>(),ForgetPwdView,View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    private fun initView(){

        //初始化mVerifyCodeBtn
        mVerifyCodeBtn.setOnVerifyBtnClick(object :VerifyButton.OnVerifyBtnClick{
            override fun onClick() {
                toast("发送验证码")
                //去短信中心请求
            }
        })
        mVerifyCodeBtn.setOnClickListener(this)

        mNextBtn.setOnClickListener(this)
        mNextBtn.enable({isBtnEnable()},mMobileEt,mVerifyCodeEt)
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNotEmpty() && mVerifyCodeEt.text.isNotEmpty()
    }

    override fun onForgetPwdResult(result: Boolean) {
        //跳转
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
            }

            R.id.mNextBtn ->{
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeBtn.text.toString())
            }
        }
    }

    override fun injectComponent() {//如果使用到了Dagger，需要在这里绑定Component
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView=this
    }
}