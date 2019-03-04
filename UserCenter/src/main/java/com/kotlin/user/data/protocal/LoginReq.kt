package com.kotlin.user.data.protocal

/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */

/**
 * 登录的请求实体
 */
data class LoginReq(val moblie:String, val pwd:String, val pushId:String) {
}