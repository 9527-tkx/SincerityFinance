package com.vf.sincerityfinance.ui.fragment.project

import android.os.Bundle
import androidx.lifecycle.LifecycleRegistry
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentProjectBinding
import com.vf.sincerityfinance.viewmodel.vm.ProjectViewModel
import com.vf.sincerityfinance.weight.ext.init
import com.zhpan.bannerview.BannerViewPager

/**
 * @program: SincerityFinance
 *
 * @description: 功能选项页面
 *
 * @author: tkx
 *
 * @create: 2021-04-06 16:54
 **/
class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {
    //banner图用的viewpager
    private var mViewPager: BannerViewPager<Int>? = null

    /**
     * 绑定布局
     * @return Int
     */
    override fun layoutId() = R.layout.fragment_project

    override fun initView(savedInstanceState: Bundle?) {
        mViewPager = view?.findViewById(R.id.banner_view)
        //使用kotlin的扩展函数封装一行代码实现banner图轮播
        mViewPager?.init(4, mViewPager!!, lifecycle as LifecycleRegistry)




    }


}