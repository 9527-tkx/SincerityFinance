package com.vf.sincerityfinance.ui.fragment.me

import android.os.Bundle
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentMeBinding
import com.vf.sincerityfinance.viewmodel.vm.MeViewModel

/**
 * @program: SincerityFinance
 *
 * @description:
 *
 * @author: tkx
 *
 * @create: 2021-04-06 17:00
 **/
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {
    override fun layoutId()=R.layout.fragment_me

    override fun initView(savedInstanceState: Bundle?) {
    }
}