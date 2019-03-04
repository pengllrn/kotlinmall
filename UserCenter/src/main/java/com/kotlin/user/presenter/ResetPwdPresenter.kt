package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.ResetPwdView
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Author：pengllrn
 * Time: 2019/3/4 16:00
 */
class ResetPwdPresenter @Inject constructor():BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService:UserServiceImpl

    fun resetPwd(mobile:String,pwd:String){
        if(!checkNetwork())
            return
        mView.showLoading()

        userService.resetPwd(mobile,pwd)//访问网络
                .excute(object :BaseSubscriber<Boolean>(mView){
                    override fun onNext(t: Boolean) {
                        mView.onResetPwdResult(t)
                    }
                },lifecyProvider)
    }
}