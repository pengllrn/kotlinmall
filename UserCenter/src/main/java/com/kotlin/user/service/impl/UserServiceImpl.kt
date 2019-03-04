package com.kotlin.user.service.impl

import com.kotlin.base.rx.BaseFunc
import com.kotlin.base.rx.BaseFuncBoolean
import com.kotlin.user.data.protocal.UserInfo
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Author：Pengllrn
 * Date: 2019/2/23
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class UserServiceImpl @Inject constructor():UserService{
    @Inject
    lateinit var repository: UserRepository

    /*
    注册
     */
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
//        val repository = UserRepository()

        //返回值与repository对应方法的返回值有些不一样，需要通过flatMap进行数据转换
        return repository.register(mobile,pwd,verifyCode)
                .flatMap(BaseFuncBoolean())
    }

    /*
    登录
     */
    //Observable<UserInfo>中的UserInfo可以更改为自定义的返回数据类型T:data，和presenter一致
    override fun login(mobile: String, pwd: String, pushId:String): Observable<UserInfo> {
        //与数据层对接
        return repository.login(mobile,pwd,pushId).flatMap(BaseFunc())
    }
}