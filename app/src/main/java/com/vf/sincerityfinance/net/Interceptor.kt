package com.vf.sincerityfinance.net

import okhttp3.Interceptor
import okhttp3.Response

import java.io.IOException

/**
 * @program: SincerityFinance
 *
 * @description: 自定义拦截器放token啥的
 *
 * @author: tkx
 *
 * @create: 2021-03-31 15:51
 **/
class FilterInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpBuilder = originalRequest.url.newBuilder()
        httpBuilder.addEncodedQueryParameter(HttpConfig.KEY, HttpConfig.KEY_MAP)
        val requestBuilder = originalRequest.newBuilder()
            .url(httpBuilder.build())
        return chain.proceed(requestBuilder.build())
    }

}