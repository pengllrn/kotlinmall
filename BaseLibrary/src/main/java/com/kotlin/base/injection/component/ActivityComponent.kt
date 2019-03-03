package com.kotlin.base.injection.component

import android.app.Activity
import com.kotlin.base.injection.ActivityScope
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Author：Pengllrn
 * Date: 2019/3/1
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class))
interface ActivityComponent {

    fun activity():Activity

    fun lifecycleProvider(): LifecycleProvider<*>
}