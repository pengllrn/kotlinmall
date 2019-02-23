package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.impl.UserServiceImpl

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class RegisterPresenter:BasePresenter<RegisterView>() {

    fun register(mobile:String,verifyCode:String,pwd:String){
        /*
        业务逻辑：请求网络，注册，接受相应
         */
        //回调
        //mView为RegisterView类型（泛型参数）的，所以能够使用RegisterView的方法
        val userService = UserServiceImpl()
        userService.register(mobile,verifyCode,pwd)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(object :BaseSubscriber<Boolean>(){
//                    override fun onNext(t: Boolean) {
//                        mView.onRegisterResult(t)
//                    }
//                })
                .excute(object :BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                })

    }
}