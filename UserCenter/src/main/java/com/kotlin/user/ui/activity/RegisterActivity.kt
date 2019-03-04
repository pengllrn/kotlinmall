package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.widgets.VerifyButton
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView,
    View.OnClickListener{

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
        startActivity<LoginActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
//        mPresenter=RegisterPresenter()  //自动注入

        initView()
    }

    private fun initView(){
        //mGetVerifyCodeBtn初始化
        mGetVerifyCodeBtn.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
            override fun onClick() {
                toast("获取验证码")
            }
        })
        //mGetVerifyCodeBtn设置点击监听
        mGetVerifyCodeBtn.setOnClickListener(this)
        //mRegisterBtn设置点击监听
        mRegisterBtn.setOnClickListener(this)

        mRegisterBtn.enable({isBtnEnable()},mMobileEt,mVerifyCodeEt,mPwdEt,mPwdConfirmEt)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mGetVerifyCodeBtn ->{
                mGetVerifyCodeBtn.requestSendVerifyNumber()
            }
            R.id.mRegisterBtn ->{
                if(mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                    toast("密码不一致")
                    return
                }
                mPresenter.register(mMobileEt.text.toString(),
                        mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
            }
        }
    }

    fun isBtnEnable():Boolean{
        return mMobileEt.text.isNotEmpty() && mVerifyCodeEt.text.isNotEmpty()
                && mPwdEt.text.isNotEmpty() && mPwdConfirmEt.text.isNotEmpty()
    }

}
