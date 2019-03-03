package com.kotlin.base.presenter

import android.content.Context
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T

    @Inject
    lateinit var lifecyProvider: LifecycleProvider<*>

    @Inject
    lateinit var context:Context

    fun checkNetwork():Boolean{
        if (NetWorkUtils.isNetWorkAvailable(context))
            return true
        else
            mView.onError("网络不可用")
        return false
    }
}