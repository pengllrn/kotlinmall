package com.kotlin.base.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Author：Pengllrn
 * Date: 2019/3/3
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider:LifecycleProvider<*>) {

    @Provides
    fun  providesLifecycleProvider():LifecycleProvider<*>{
        return lifecycleProvider
    }
}