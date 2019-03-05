package com.kotlin.base.data.net

import com.kotlin.base.common.BaseConstant
import com.kotlin.base.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author：Pengllrn
 * Date: 2019/2/25
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */
class RetrofitFactory private constructor(){
    companion object {
        val instance:RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit:Retrofit
    private val interceptor:Interceptor//Okhttp3拦截器

    init {
        interceptor = Interceptor {
            chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("charset","utf-8")
                    .addHeader("token",AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())  //添加数据转换工程
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加Rx适配器
                .client(initClient())  //初始化okhttp3
                .build()
    }

    private fun initClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor()) //添加Okhttp日志拦截器1
                .addInterceptor(interceptor) ////添加Okhttp网络拦截器1
                .connectTimeout(10,TimeUnit.SECONDS) //网络连接超时事件
                .readTimeout(10,TimeUnit.SECONDS)  //
                .build()
    }

    private fun initLogInterceptor(): Interceptor {//构建日志拦截器
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY  //要打印的日志级别
        return interceptor
    }

    fun <T> create(service: Class<T>):T{
        return retrofit.create(service)
    }
}