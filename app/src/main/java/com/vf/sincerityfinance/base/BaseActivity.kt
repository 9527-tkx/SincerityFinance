package com.vf.sincerityfinance.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gyf.immersionbar.ImmersionBar
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * @program: SincerityFinance
 *
 * @description: baseActivity （可能还会加东西）
 *
 * @author: tkx
 *
 * @create: 2021-04-01 17:29
 **/
abstract class BaseActivity<VM:BaseViewModel,DB:ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

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
        TODO()
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