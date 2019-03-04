package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.data.protocal.UserInfo
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Author：Pengllrn
 * Date: 2019/3/4
 */
class LoginPresenter @Inject constructor():BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserServiceImpl  //不能是私有的，错误: Dagger does not support injection into private fields

    fun login(mobile:String,pwd:String){
        if(!checkNetwork()){
            return
        }
        mView.showLoading()

        userService.login(mobile,pwd,"")//访问网络
                .excute(object :BaseSubscriber<UserInfo>(mView){//UserInfo与login的返回结果一致
                    override fun onNext(t: UserInfo) {//网络访问成功status=0，UserInfo与login的返回结果一致
                        mView.onLoginResult(t)  //执行LoginView中的回调方法，在Activity中去实现
                    }
                },lifecyProvider)  //处理返回结果
    }
}