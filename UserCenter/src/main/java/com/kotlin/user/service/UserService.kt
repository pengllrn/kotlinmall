package com.kotlin.user.service

import com.kotlin.user.data.protocal.UserInfo
import rx.Observable

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
interface UserService {
    fun register(mobile:String,pwd:String,verifyCode:String):Observable<Boolean>

    fun login(mobile: String,pwd: String,pushId:String):Observable<UserInfo>

    fun forgetPwd(mobile: String,verifyCode: String):Observable<Boolean>

    fun resetPwd(mobile: String,pwd: String):Observable<Boolean>


}