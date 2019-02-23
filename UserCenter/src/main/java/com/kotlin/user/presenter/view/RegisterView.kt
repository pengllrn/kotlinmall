package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
interface RegisterView:BaseView {
    //    回调
    fun onRegisterResult(result:Boolean)
}