package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.ForgetPwdView
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Author：pengllrn
 * Time: 2019/3/4 16:00
 */
class ForgetPwdPresenter @Inject constructor():BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService:UserServiceImpl

    fun forgetPwd(mobile:String,verifyCode:String){
        if(!checkNetwork())
            return
        mView.showLoading()

        userService.forgetPwd(mobile,verifyCode)//访问网络
                .excute(object :BaseSubscriber<Boolean>(mView){
                    override fun onNext(t: Boolean) {
                        mView.onForgetPwdResult(t)
                    }
                },lifecyProvider)
    }
}