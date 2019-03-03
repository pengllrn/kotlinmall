package com.kotlin.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author：Pengllrn
 * Date: 2019/3/1
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @Singleton
    fun providesActivity():Activity{
        return activity
    }
}