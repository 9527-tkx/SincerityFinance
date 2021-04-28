package com.vf.sincerityfinance.ui.fragment

import android.os.Bundle
import com.qmuiteam.qmui.kotlin.onClick
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentChangecompanyBinding
import com.vf.sincerityfinance.viewmodel.vm.SettingsViewModel
import com.vf.sincerityfinance.weight.ext.nav
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * @program: SincerityFinance
 *
 * @description: 设置
 *
 * @author: tkx
 *
 * @create: 2021-04-28 15:32
 **/
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentChangecompanyBinding>() {
    override fun layoutId()= R.layout.fragment_settings

    override fun initView(savedInstanceState: Bundle?) {
        iv_back.onClick {
            nav().navigateUp()
        }
    }
}