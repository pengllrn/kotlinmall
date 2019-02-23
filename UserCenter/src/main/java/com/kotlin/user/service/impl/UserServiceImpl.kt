package com.kotlin.user.service.impl

import com.kotlin.user.service.UserService
import rx.Observable

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class UserServiceImpl:UserService{
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return Observable.just(true)
    }
}