package com.vf.sincerityfinance.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentMainBinding
import com.vf.sincerityfinance.viewmodel.appViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import com.vf.sincerityfinance.viewmodel.vm.MainViewModel
import com.vf.sincerityfinance.weight.ext.init
import com.vf.sincerityfinance.weight.ext.initMain
import com.vf.sincerityfinance.weight.ext.interceptLongClick
import com.vf.sincerityfinance.weight.ext.setUiTheme

/**
 * 时间　: 2021
 * 作者　: tkx
 * 描述　:项目主页Fragment
 */
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override fun layoutId() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        //初始化 bottomBar
        mainBottom.init{
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_project -> mainViewpager.setCurrentItem(1, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(2, false)
            }
        }
        mainBottom.interceptLongClick(R.id.menu_main,R.id.menu_project,R.id.menu_me)
    }

    override fun createObserver() {
        appViewModel.appColor.observeInFragment(this, Observer {
            setUiTheme(it, mainBottom)
        })
    }

}