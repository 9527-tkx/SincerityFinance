package com.vf.sincerityfinance

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.tencent.bugly.beta.Beta
import com.vf.sincerityfinance.R.id.*
import com.vf.sincerityfinance.base.BaseActivity
import com.vf.sincerityfinance.databinding.ActivityMainBinding
import com.vf.sincerityfinance.utils.LottieAnimation.*
import com.vf.sincerityfinance.utils.StatusBarUtil
import com.vf.sincerityfinance.viewmodel.appViewModel
import com.vf.sincerityfinance.viewmodel.vm.MainViewModel
import com.vf.sincerityfinance.weight.ext.init
import me.hgj.jetpackmvvm.network.manager.NetState

/**
 * 主activity 就这一个activity其他fragment都靠它
 * @property exitTime Long
 * @author tkx
 */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private var exitTime = 0L
    private lateinit var nav: NavController


    override fun layoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        //进入首页检查更新
        Beta.checkUpgrade(false, true)
        //沉浸式
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        nav = Navigation.findNavController(this@MainActivity, nav_host_fragment)

        //初始化bottomView
        mDatabind.mainBottom.init(nav,navigation_home, navigation_dashboard,navigation_notifications)

        // 后退判断
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (nav.currentDestination != null && nav.currentDestination!!.id != navigation_home) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
        appViewModel.appColor.value?.let {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            supportActionBar?.setBackgroundDrawable(ColorDrawable(it))
            StatusBarUtil.setColor(this, it, 0)
        }
    }

    override fun createObserver() {
        appViewModel.appColor.observeInActivity(this, Observer {
            supportActionBar?.setBackgroundDrawable(ColorDrawable(it))
            StatusBarUtil.setColor(this, it, 0)
        })
    }

    /**
     * 示例，在Activity/Fragment中如果想监听网络变化，可重写onNetworkStateChanged该方法
     */
    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            makeText(applicationContext, "我特么终于有网了啊!", Toast.LENGTH_SHORT).show()
        } else {
            makeText(applicationContext, "我特么怎么断网了!", Toast.LENGTH_SHORT).show()
        }
    }

}