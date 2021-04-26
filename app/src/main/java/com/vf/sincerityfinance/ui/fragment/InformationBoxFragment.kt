package com.vf.sincerityfinance.ui.fragment

import android.os.Bundle
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentInformationboxBinding
import com.vf.sincerityfinance.viewmodel.vm.InformationBoxViewModel
import com.vf.sincerityfinance.weight.ext.nav
import kotlinx.android.synthetic.main.fragment_morenotice.*

/**
 * @program: SincerityFinance
 *
 * @description: 信息盒
 *
 * @author: tkx
 *
 * @create: 2021-04-26 17:26
 **/
class InformationBoxFragment :
    BaseFragment<InformationBoxViewModel, FragmentInformationboxBinding>() {
    override fun layoutId() = R.layout.fragment_informationbox

    override fun initView(savedInstanceState: Bundle?) {
        QMUIStatusBarHelper.translucent(this.activity)
        QMUIStatusBarHelper.setStatusBarLightMode(this.activity)
        iv_back.run {
            onClick {
                nav().navigateUp()
            }
        }
    }

}