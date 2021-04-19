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
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.airbnb.lottie.LottieDrawable
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.tencent.bugly.beta.Beta
import com.vf.sincerityfinance.R.id.*
import com.vf.sincerityfinance.base.BaseActivity
import com.vf.sincerityfinance.databinding.ActivityMainBinding
import com.vf.sincerityfinance.utils.AnimationUtil.getLottieDrawable
import com.vf.sincerityfinance.utils.LottieAnimation
import com.vf.sincerityfinance.utils.LottieAnimation.*
import com.vf.sincerityfinance.utils.StatusBarUtil
import com.vf.sincerityfinance.utils.getLottieAnimationList
import com.vf.sincerityfinance.viewmodel.appViewModel
import com.vf.sincerityfinance.viewmodel.vm.MainViewModel
import com.vf.sincerityfinance.weight.ext.init
import com.vf.sincerityfinance.weight.ext.interceptLongClick
import kotlinx.coroutines.selects.select
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.network.manager.NetState

/**
 * 主activity 就这一个activity其他fragment都靠它
 * @property exitTime Long
 * @author tkx
 */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private var exitTime = 0L
    var mPreClickPosition = 0
    private lateinit var nav:NavController



    override fun layoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        //进入首页检查更新
        Beta.checkUpgrade(false, true)
        //沉浸式
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        //初始化bottomView
        mDatabind.mainBottom.init(
            this,
        )
        nav = Navigation.findNavController(this@MainActivity, nav_host_fragment)
//        mDatabind.mainBottom.setOnNavigationItemSelectedListener {
//            // 避免再次点击重复创建
//            if (it.isChecked) {
//                return@setOnNavigationItemSelectedListener true
//            }
//            // 避免B返回到A重复创建
//            val popBackStack = nav.popBackStack(it.itemId, false)
//            if (popBackStack) {
//                // 已创建
//                return@setOnNavigationItemSelectedListener popBackStack
//            } else {
//                // 未创建
//                return@setOnNavigationItemSelectedListener NavigationUI.onNavDestinationSelected(
//                    it, nav)
//            }
//        }



        //清除bottom自带的图标长按事件
        mDatabind.mainBottom.interceptLongClick(
            navigation_home, navigation_dashboard,
            navigation_notifications
        )

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

    /**
     * @dec 点击item时动态加载动画以及fragment跟随图标切换
     * @param item MenuItem
     * @return Boolean
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.icon = getLottieDrawable(item, mDatabind.mainBottom, ::select)
        noselect(item)
        var icon = item.icon as? LottieDrawable
        icon?.apply {
            playAnimation()
        }
        if (item.isChecked) {
            return true
        }
        // 避免B返回到A重复创建
        val popBackStack = nav.popBackStack(item.itemId, false)
        if (popBackStack) {
            // 已创建
            return popBackStack
        } else {
            // 未创建
            return NavigationUI.onNavDestinationSelected(
                item, nav)
        }
    }

    /**
     * @desc 动画对应选择器（好响亮的名字）
     * @param menuItem MenuItem
     * @return LottieAnimation
     */
    fun select(menuItem: MenuItem): LottieAnimation {

        return when (menuItem.itemId) {
            navigation_home -> HOME
            navigation_dashboard -> PROJECT
            navigation_notifications -> ME

            else -> HOME
        }
    }

    /**
     * @desc 过滤掉当前选择item
     * @param menuItem MenuItem
     * @return Unit
     */
    fun noselect(menuItem: MenuItem) {
        when (menuItem.itemId) {
            navigation_home -> {
                mDatabind.mainBottom.menu.findItem(navigation_dashboard)
                    .setIcon(R.mipmap.project_unalready)
                mDatabind.mainBottom.menu.findItem(navigation_notifications)
                    .setIcon(R.mipmap.me_unalready)
            }
            navigation_dashboard -> {
                mDatabind.mainBottom.menu.findItem(navigation_home)
                    .setIcon(R.mipmap.home_unalready)
                mDatabind.mainBottom.menu.findItem(navigation_notifications)
                    .setIcon(R.mipmap.me_unalready)
            }
            navigation_notifications -> {
                mDatabind.mainBottom.menu.findItem(navigation_dashboard)
                    .setIcon(R.mipmap.project_unalready)
                mDatabind.mainBottom.menu.findItem(navigation_home)
                    .setIcon(R.mipmap.home_unalready)
            }


        }
    }


}