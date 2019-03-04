package com.kotlin.user.data.protocal

/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */

/**
 * 注册请求，提供三个参数
 */
data class RegisterReq(val mobile:String,val pwd:String,val verifyCode:String) {
}