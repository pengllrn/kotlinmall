package com.kotlin.base.common

import android.app.Application

/**
 * Author：Pengllrn
 * Date: 2019/3/1
 */
class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        initAppInjection()
    }

    private fun initAppInjection() {
        
    }
}