package com.kotlin.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * Author：Pengllrn
 * Date: 2019/3/3
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope