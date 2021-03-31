package com.vf.sincerityfinance

import android.app.Application
import android.content.Context

/**
 * @program: SincerityFinance
 *
 * @description:
 *
 * @author: tkx
 *
 * @create: 2021-03-31 16:17
 **/
class MyApplication: Application() {
    companion object {

        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}