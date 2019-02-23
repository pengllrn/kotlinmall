package com.kotlin.base.ui.activity

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity() ,BaseView{
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }

    lateinit var mPresenter: T

}