package com.vf.sincerityfinance.ui.fragment

import android.os.Bundle
import com.qmuiteam.qmui.kotlin.onClick
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentChangecompanyBinding
import com.vf.sincerityfinance.viewmodel.vm.ChangeCompanyViewModel
import com.vf.sincerityfinance.weight.ext.nav
import kotlinx.android.synthetic.main.fragment_morenotice.*

/**
 * @program: SincerityFinance
 *
 * @description: 更改公司
 *
 * @author: tkx
 *
 * @create: 2021-04-28 15:17
 **/
class ChangeCompanyFragment : BaseFragment<ChangeCompanyViewModel, FragmentChangecompanyBinding>() {
    override fun layoutId()= R.layout.fragment_changecompany

    override fun initView(savedInstanceState: Bundle?) {
        iv_back.run {
            onClick {
                nav().navigateUp()
            }
        }

    }
}