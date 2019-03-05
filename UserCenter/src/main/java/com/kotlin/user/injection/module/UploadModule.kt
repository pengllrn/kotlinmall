package com.kotlin.user.injection.module

import com.kotlin.user.service.UploadService
import com.kotlin.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author：Pengllrn
 * Date: 2019/2/28
 */

@Module
class UploadModule {

    @Provides
    fun providesUploadService(uploadService: UploadServiceImpl):UploadService{
        return uploadService
    }
}