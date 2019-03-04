package com.kotlin.user.presenter

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Author：pengllrn
 * Time: 2019/3/4 19:48
 */
class UserInfoPresenter @Inject constructor():BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserServiceImpl



}