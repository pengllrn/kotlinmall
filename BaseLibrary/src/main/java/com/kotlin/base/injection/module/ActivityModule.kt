package com.kotlin.base.injection.module

import android.app.Activity
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Author：Pengllrn
 * Date: 2019/3/1
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesActivity():Activity{
        return activity
    }
}