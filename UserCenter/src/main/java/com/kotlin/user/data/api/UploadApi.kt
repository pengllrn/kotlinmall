package com.kotlin.user.data.api

import com.kotlin.base.data.protocal.BaseResponse
import retrofit2.http.POST
import rx.Observable


/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResponse<String>>

}