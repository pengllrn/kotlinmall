package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocal.BaseResponse
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocal.*
import rx.Observable
import javax.inject.Inject

/**
 * 仓库：直接用来访问网络，数据层的最终封装。
 * Author：Pengllrn
 * Date: 2019/2/25
 */
class UserRepository @Inject constructor(){
    fun register(mobile:String,pwd:String,verifyCode:String)
            : Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile,pwd,verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId:String):Observable<BaseResponse<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(mobile,pwd,""))
    }

    fun forgetPwd(mobile: String,verifyCode: String):Observable<BaseResponse<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetpwd(ForgetPwdReq(mobile,verifyCode))
    }

    fun resetPwd(mobile: String,pwd: String):Observable<BaseResponse<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetpwd(ResetPwdReq(mobile,pwd))
    }

    fun editUser(userIcon: String, userName: String, UserGender: String,
                 userSign: String):Observable<BaseResponse<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUser(EditUserReq(userIcon,userName,UserGender,userSign))
    }

}