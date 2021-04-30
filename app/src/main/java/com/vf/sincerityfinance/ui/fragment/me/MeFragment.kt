package com.vf.sincerityfinance.ui.fragment.me

import android.os.Bundle
import android.widget.Toast
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.tencent.bugly.Bugly.applicationContext
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentMeBinding
import com.vf.sincerityfinance.viewmodel.vm.MeViewModel
import com.vf.sincerityfinance.weight.ext.nav
import com.vf.sincerityfinance.weight.ext.navigateAction
import kotlinx.android.synthetic.main.fragment_me.*

/**
 * @program: SincerityFinance
 *
 * @description: 我的页面
 *
 * @author: tkx
 *
 * @create: 2021-04-06 17:00
 **/
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {
    
    override fun layoutId() = R.layout.fragment_me

    override fun initView(savedInstanceState: Bundle?) {
        QMUIStatusBarHelper.translucent(this.activity)
        QMUIStatusBarHelper.setStatusBarLightMode(this.activity)
        bt_unlogin.run {
            onClick {
                nav().navigateAction(
                    R.id.action_navigation_notifications_to_loginFragment
                )
            }
        }
        rl_changeCompany.run {
            onClick {
                nav().navigateAction(
                    R.id.action_navigation_notifications_to_changecompanyFragment
                )
            }
        }
        iv_settings.run {
            onClick {
                nav().navigateAction(
                    R.id.action_navigation_notifications_to_settingFragment
                )
            }
        }
        rl_up.run {
            onClick {
                Toast.makeText(applicationContext, "您已经是最新版了！", Toast.LENGTH_SHORT).show()
            }
        }


    }
}