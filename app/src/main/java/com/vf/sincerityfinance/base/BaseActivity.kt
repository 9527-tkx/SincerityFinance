package com.vf.sincerityfinance.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * @program: SincerityFinance
 *
 * @description:
 *
 * @author: tkx
 *
 * @create: 2021-04-01 17:29
 **/
class BaseActivity<VM:BaseViewModel,DB:ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    /**
     * 创建liveData观察者
     */

    override fun createObserver() {
        TODO("Not yet implemented")
    }
    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun initView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun layoutId(): Int {
        TODO("Not yet implemented")
    }
    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        TODO("Not yet implemented")
    }
}