package com.vf.sincerityfinance

import android.animation.Animator
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
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
import com.vf.sincerityfinance.utils.AnimationUtil.DURATION_300
import com.vf.sincerityfinance.utils.AnimationUtil.translationY
import com.vf.sincerityfinance.utils.LottieAnimation.*
import com.vf.sincerityfinance.utils.StatusBarUtil
import com.vf.sincerityfinance.viewmodel.appViewModel
import com.vf.sincerityfinance.viewmodel.vm.MainViewModel
import com.vf.sincerityfinance.weight.ext.init
import com.vf.sincerityfinance.weight.ext.nav
import me.hgj.jetpackmvvm.network.manager.NetState

/**
 * 主activity 就这一个activity其他fragment都靠它
 * @property exitTime Long
 * @author tkx
 */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    /**
     * BottomNav显示控制
     */
    private var isShowBottomNav = true
    private var exitTime = 0L
    private lateinit var nav: NavController


    override fun layoutId() = R.layout.activity_main

    @SuppressLint("ResourceAsColor")
    override fun initView(savedInstanceState: Bundle?) {
        //进入首页检查更新
        Beta.checkUpgrade(false, true)
        //沉浸式
        nav = Navigation.findNavController(this@MainActivity, nav_host_fragment)

        //初始化bottomView
        mDatabind.mainBottom.init(
            nav,
            navigation_home,
            navigation_dashboard,
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

        //跳转非主页时隐藏bottomnavigation 跳转回来时候显示 加个动画 不会那么唐突
        nav.addOnDestinationChangedListener { _, destination, _ ->
            // 显示或隐藏导航
            when (destination.id) {
                navigation_home, navigation_dashboard, navigation_notifications -> {
                    showBottomNav()
                }
                else -> hideBottomNav()
            }
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
            makeText(applicationContext, "连上了", Toast.LENGTH_SHORT).show()
        } else {
            makeText(applicationContext, "你断网了!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 显示导航
     */
    fun showBottomNav() {
        if (!isShowBottomNav) {
            val translationY = translationY(
                mDatabind.mainBottom, mDatabind.mainBottom.height.toFloat(), 0F,
                DURATION_300
            )
            translationY.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    mDatabind.mainBottom.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }
            })
            translationY.start()
            isShowBottomNav = true
        }
    }

    /**
     * 隐藏导航
     */
    private fun hideBottomNav() {
        if (isShowBottomNav) {
            val translationY = translationY(
                mDatabind.mainBottom, 0F, mDatabind.mainBottom.height.toFloat(),
                DURATION_300
            )
            translationY.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    mDatabind.mainBottom.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }


            })
            translationY.start()
            isShowBottomNav = false
        }
    }

}