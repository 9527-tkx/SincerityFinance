package com.vf.sincerityfinance.net

import android.widget.Toast
import com.vf.sincerityfinance.MyApplication
import com.vf.sincerityfinance.service.ApiService
import github.leavesc.monitor.MonitorInterceptor
import github.leavesc.reactivehttp.datasource.RemoteExtendDataSource
import github.leavesc.reactivehttp.viewmodel.IUIActionEvent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @program: SincerityFinance
 *
 * @description: 自定义的
 *
 * @author: tkx
 *
 * @create: 2021-03-31 16:14
 **/
class MyRemoteDataSource(iActionEvent: IUIActionEvent?) : RemoteExtendDataSource<ApiService>(iActionEvent, ApiService::class.java) {

    companion object {

        private val httpClient: OkHttpClient by lazy {
            createHttpClient()
        }

        private fun createHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .readTimeout(1000L, TimeUnit.MILLISECONDS)
                .writeTimeout(1000L, TimeUnit.MILLISECONDS)
                .connectTimeout(1000L, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(FilterInterceptor())
                .addInterceptor(MonitorInterceptor(MyApplication.context))
            return builder.build()
        }

    }

    /**
     * 由子类实现此字段以便获取 baseUrl
     */
    override val baseUrl: String
        get() = HttpConfig.BASE_URL

    /**
     * 允许子类自己来实现创建 Retrofit 的逻辑
     * 外部无需缓存 Retrofit 实例，ReactiveHttp 内部已做好缓存处理
     * 但外部需要自己判断是否需要对 OKHttpClient 进行缓存
     * @param baseUrl
     */
    override fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun showToast(msg: String) {
        Toast.makeText(MyApplication.context, msg, Toast.LENGTH_SHORT).show()
    }

}