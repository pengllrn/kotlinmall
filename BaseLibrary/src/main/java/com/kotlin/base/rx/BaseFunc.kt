package com.kotlin.base.rx

import com.kotlin.base.data.protocal.BaseResponse
import rx.Observable
import rx.functions.Func1

/**
 * Author：Pengllrn
 * Date: 2019/3/3
 */
class BaseFunc<T>:Func1<BaseResponse<T>,Observable<T>> {
    override fun call(t: BaseResponse<T>): Observable<T>{
        if (t.status != 0){
            return Observable.error(BaseException(t.status,t.message))
        }
        return Observable.just(t.data)
    }

}