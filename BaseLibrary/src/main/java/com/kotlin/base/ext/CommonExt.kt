package com.kotlin.base.ext

import com.kotlin.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */

fun <T> Observable<T>.excute(subscriber:BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())   //使用的时候，需要传入lifecycleProvider对象，因为直接传太麻烦，通过注入
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}