package com.kotlin.user.injection.component

import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * Author：Pengllrn
 * Date: 2019/2/28
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {

    //要注册的地方
    fun inject(activity:RegisterActivity)
}