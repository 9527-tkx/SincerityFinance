package com.vf.sincerityfinance.ui.fragment

import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentMorenoticeBinding
import com.vf.sincerityfinance.viewmodel.vm.MoreNoticeViewModel
import com.vf.sincerityfinance.weight.ext.nav
import kotlinx.android.synthetic.main.fragment_morenotice.*
import me.hgj.jetpackmvvm.ext.view.gone

/**
 * @program: SincerityFinance
 *
 * @description: 最新消息-更多-企业动态
 *
 * @author: tkx
 *
 * @create: 2021-04-26 15:04
 **/
class MoreNoticeFragment : BaseFragment<MoreNoticeViewModel, FragmentMorenoticeBinding>() {

    override fun layoutId() = R.layout.fragment_morenotice

    override fun initView(savedInstanceState: Bundle?) {
        QMUIStatusBarHelper.translucent(this.activity)
        QMUIStatusBarHelper.setStatusBarLightMode(this.activity)
        iv_back.run {
            onClick {
                nav().navigateUp()
            }
        }
        rl_check1.onClick {
            it.visibility=View.GONE
            rl_check2.visibility=View.VISIBLE
        }
        rl_check2.onClick {
            it.visibility=View.GONE
            rl_check1.visibility=View.VISIBLE
        }
    }
}