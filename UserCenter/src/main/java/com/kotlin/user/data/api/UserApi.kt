package com.kotlin.user.data.api

import com.kotlin.base.data.protocal.BaseResponse
import com.kotlin.user.data.protocal.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req:RegisterReq): Observable<BaseResponse<String>>
}