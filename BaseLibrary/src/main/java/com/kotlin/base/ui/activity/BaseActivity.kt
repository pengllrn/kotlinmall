package com.kotlin.base.ui.activity

import android.os.Bundle
import com.kotlin.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
open class BaseActivity:RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.INSTANCE.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.INSTANCE.removeActivity(this)
    }
}