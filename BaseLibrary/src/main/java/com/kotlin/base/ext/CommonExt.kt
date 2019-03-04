package com.kotlin.base.ext

import android.widget.Button
import android.widget.EditText
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 */

fun <T> Observable<T>.excute(subscriber:BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())   //使用的时候，需要传入lifecycleProvider对象，因为直接传太麻烦，通过注入
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}


/**
 * 为Button设置一个TextWatcher，根据传入的判断方法，以及监听的EditText，设置Button的状态
 * @param method 判断方法
 * @param ets 要监听的EditText
 */
fun Button.enable(method:() -> Boolean,vararg ets:EditText){
    val btn = this
    ets.forEach {
        it.addTextChangedListener(object : DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn.isEnabled = method()
            }
        })
    }
}