package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 */
class RegisterPresenter @Inject constructor():BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService:UserServiceImpl

    fun register(mobile:String,verifyCode:String,pwd:String){
        /*
        业务逻辑：请求网络，注册，接受相应
         */
        //回调
        //mView为RegisterView类型（泛型参数）的，所以能够使用RegisterView的方法
//        val userService = UserServiceImpl()
        userService.register(mobile,pwd,verifyCode)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(object :BaseSubscriber<Boolean>(){
//                    override fun onNext(t: Boolean) {
//                        mView.onRegisterResult(t)
//                    }
//                })
                .excute(object :BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        if(t) mView.onRegisterResult("注册成功")
                    }
                },lifecyProvider)

    }
}