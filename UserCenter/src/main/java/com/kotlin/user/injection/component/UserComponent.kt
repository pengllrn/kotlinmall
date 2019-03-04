package com.kotlin.user.injection.component

import com.kotlin.base.injection.PerComponetScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.ui.activity.ForgetPwdActivity
import com.kotlin.user.ui.activity.LoginActivity
import com.kotlin.user.ui.activity.RegisterActivity
import com.kotlin.user.ui.activity.ResetPwdActivity
import dagger.Component

/**
 * Author：Pengllrn
 * Date: 2019/2/28
 */
@PerComponetScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(UserModule::class))
interface UserComponent {

    //要注册的地方
    fun inject(activity:RegisterActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: ForgetPwdActivity)

    fun inject(activity: ResetPwdActivity)
}