package com.kotlin.user.ui.activity

import android.os.Bundle
import com.kotlin.base.common.AppManager
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.widgets.VerifyButton
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
//        mPresenter=RegisterPresenter()

        mRegisterBtn.setOnClickListener {
            mPresenter.register(mMobileEt.text.toString(),
                    mVerifyCodeEt.text.toString(),mPwdEt.text.toString())

        }

        //mGetVerifyCodeBtn初始化
        mGetVerifyCodeBtn.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
            override fun onClick() {
                toast("获取验证码")
            }
        })
        //mGetVerifyCodeBtn设置点击监听
        mGetVerifyCodeBtn.setOnClickListener { mGetVerifyCodeBtn.requestSendVerifyNumber() }


    }

    override fun onBackPressed() {
        AppManager.INSTANCE.exitApp(this)
    }

}
