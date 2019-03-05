package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Author：pengllrn
 * Time: 2019/3/4 19:47
 */
interface UserInfoView:BaseView {

    fun onGetUploadTokenResult(result:String)
}