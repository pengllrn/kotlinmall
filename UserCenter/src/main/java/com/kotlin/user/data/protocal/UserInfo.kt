package com.kotlin.user.data.protocal

/**
 * Author：Pengllrn
 * Date: 2019/3/4
 * 用户实体类
 * @constructor
 *
 */
data class UserInfo(val id:String,
                    val userIcon:String,
                    val userName:String,
                    val userGender:String,
                    val userMobile:String,
                    val userSign:String)