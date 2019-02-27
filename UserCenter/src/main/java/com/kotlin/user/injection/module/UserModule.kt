package com.kotlin.user.injection.module

import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author：Pengllrn
 * Date: 2019/2/28
 */

@Module
class UserModule {

    @Provides
    fun providesUserService(service : UserServiceImpl):UserService{
        return service
    }
}