package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.data.protocal.UserInfo
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.service.impl.UploadServiceImpl
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Author：pengllrn
 * Time: 2019/3/4 19:48
 */
class UserInfoPresenter @Inject constructor():BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    @Inject
    lateinit var uploadService: UploadServiceImpl

    fun getUploadToken(){
        if(!checkNetwork())
            return
        mView.showLoading()
        uploadService.getUploadToken()
                .excute(object : BaseSubscriber<String>(mView){
                    override fun onNext(t: String) {
                        super.onNext(t)
                        mView.onGetUploadTokenResult(t)
                    }
                },lifecyProvider)
    }

    fun saveUser(userIcon: String, userName: String, UserGender: String,
                 userSign: String){
        if(!checkNetwork())
            return
        mView.showLoading()

        userService.editUser(userIcon,userName,UserGender,userSign)
                .excute(object : BaseSubscriber<UserInfo>(mView){
                    override fun onNext(t: UserInfo) {
                        mView.onSaveUserInfo(t)
                    }
                },lifecyProvider)
    }



}