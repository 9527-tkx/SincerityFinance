package com.vf.sincerityfinance.net

import com.google.gson.annotations.SerializedName
import github.leavesc.reactivehttp.bean.IHttpWrapBean

/**
 * @program: SincerityFinance
 *
 * @description:随便写的请求bean
 *
 * @author: tkx
 *
 * @create: 2021-03-31 13:56
 **/
class HttpWrapBean<T>(
    @SerializedName("status") var code: Int = 0,
    @SerializedName("info") var message: String? = null,
    @SerializedName("districts", alternate = ["forecasts"]) var data: T) : IHttpWrapBean<T> {

    companion object {

        fun <T> success(data: T): HttpWrapBean<T> {
            return HttpWrapBean(HttpConfig.CODE_SERVER_SUCCESS, "success", data)
        }

        fun <T> failed(data: T): HttpWrapBean<T> {
            return HttpWrapBean(-200, "faild~~", data)
        }

    }

    override val httpCode: Int
        get() = code

    override val httpMsg: String
        get() = message ?: ""

    override val httpData: T
        get() = data

    override val httpIsSuccess: Boolean
        get() = code == HttpConfig.CODE_SERVER_SUCCESS || message == "OK"

    override fun toString(): String {
        return "HttpResBean(code=$code, message=$message, data=$data)"
    }

}

