package com.kotlin.user.service.impl

import com.kotlin.base.rx.BaseFunc
import com.kotlin.user.data.repository.UploadRepository
import com.kotlin.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class UploadServiceImpl @Inject constructor():UploadService{
    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String>{
//        val repository = UserRepository()
        //返回值与repository对应方法的返回值有些不一样，需要通过flatMap进行数据转换
        return repository.getUploadToken()
                .flatMap(BaseFunc())
    }


}