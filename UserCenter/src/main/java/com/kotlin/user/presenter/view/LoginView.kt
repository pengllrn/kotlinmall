package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocal.UserInfo

/**
 * Author：Pengllrn
 * Date: 2019/3/4
 */
interface LoginView:BaseView {
    //登录成功回调
    fun onLoginResult(result: UserInfo)
}