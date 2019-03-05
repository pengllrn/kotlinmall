package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocal.UserInfo

/**
 * Author：pengllrn
 * Time: 2019/3/4 19:47
 */
interface UserInfoView:BaseView {

    //得到凭证
    fun onGetUploadTokenResult(result:String)

    //保存资料成功
    fun onSaveUserInfo(result: UserInfo)
}