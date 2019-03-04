package com.kotlin.user.ui.activity

import android.os.Bundle
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ResetPwdPresenter
import com.kotlin.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * Author：pengllrn
 * Time: 2019/3/4 15:56
 */
class ResetPwdActivity:BaseMvpActivity<ResetPwdPresenter>(),ResetPwdView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }

    private fun initView(){

        mConfirmBtn.setOnClickListener{
            if(mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                toast("密码不一致")
                return@setOnClickListener
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
        }
        mConfirmBtn.enable({isBtnEnable()},mPwdEt,mPwdConfirmEt)
    }

    private fun isBtnEnable():Boolean{
        return mPwdEt.text.isNotEmpty() && mPwdConfirmEt.text.isNotEmpty()
    }

    override fun onResetPwdResult(result: Boolean) {
        if(result) toast("重置密码成功")
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
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