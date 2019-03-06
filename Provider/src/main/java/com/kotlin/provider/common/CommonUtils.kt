package com.kotlin.provider.common

import com.kotlin.base.common.BaseConstant
import com.kotlin.base.utils.AppPrefsUtils

/**
 * Author：pengllrn
 * Time: 2019/3/6 20:14
 */

fun isLogined():Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}