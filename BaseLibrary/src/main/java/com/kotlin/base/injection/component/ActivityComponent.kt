package com.kotlin.base.injection.component

import android.content.Context
import com.kotlin.base.injection.ActivityScope
import com.kotlin.base.injection.module.ActivityModule
import dagger.Component

/**
 * Author：Pengllrn
 * Date: 2019/3/1
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun context():Context
}