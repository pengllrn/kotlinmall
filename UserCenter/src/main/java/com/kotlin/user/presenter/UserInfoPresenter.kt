package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
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



}