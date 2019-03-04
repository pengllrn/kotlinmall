package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Author：pengllrn
 * Time: 2019/3/4 15:57
 */
interface ResetPwdView:BaseView {

    //忘记密码
    fun onResetPwdResult(result:Boolean)
}