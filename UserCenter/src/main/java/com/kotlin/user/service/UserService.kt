package com.kotlin.user.service

import rx.Observable

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
interface UserService {
    fun register(mobile:String,pwd:String,verifyCode:String):Observable<Boolean>
}