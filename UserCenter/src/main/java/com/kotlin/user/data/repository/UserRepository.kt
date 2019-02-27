package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocal.BaseResponse
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocal.RegisterReq
import rx.Observable
import javax.inject.Inject

/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class UserRepository @Inject constructor(){
    fun register(mobile:String,pwd:String,verifyCode:String)
            : Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile,pwd,verifyCode))
    }
}