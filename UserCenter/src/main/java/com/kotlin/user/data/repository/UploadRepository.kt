package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocal.BaseResponse
import com.kotlin.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * 仓库：直接用来访问网络，数据层的最终封装。
 * Author：Pengllrn
 * Date: 2019/2/25
 */
class UploadRepository @Inject constructor(){
    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }
}