package com.kotlin.base.data.protocal

/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class BaseResponse<out T>(val status:Int, val message:String, val data:T) {
}