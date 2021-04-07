package com.vf.sincerityfinance.ui.fragment.project

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.adapter.ViewBindingSampleAdapter
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentProjectBinding
import com.vf.sincerityfinance.utils.SettingUtil.getColor
import com.vf.sincerityfinance.viewmodel.vm.ProjectViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.annotation.APageStyle
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode

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


    override fun layoutId() = R.layout.fragment_project

    override fun initView(savedInstanceState: Bundle?) {
        mViewPager = view?.findViewById(R.id.banner_view)

        mViewPager?.let {
            it.setIndicatorSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorSliderColor(
                    resources.getColor(R.color.gray_1),
                    resources.getColor(R.color.tkx_like)
                )
                .setIndicatorStyle(1)
                .setIndicatorSliderRadius(
                    resources.getDimensionPixelOffset(R.dimen.dp_4),
                    resources.getDimensionPixelOffset(R.dimen.dp_4)
                )
                ?.setLifecycleRegistry(lifecycle)
                ?.setOnPageClickListener { view: View, position: Int ->
                    pageClick(
                        view,
                        position
                    )
                }
                ?.setAdapter(ViewBindingSampleAdapter(resources.getDimensionPixelOffset(R.dimen.dp_8)))
                ?.setInterval(5000)
        }
        setupBanner(PageStyle.MULTI_PAGE_OVERLAP)


    }

    private fun pageClick(view: View, position: Int) {
        if (position != mViewPager!!.currentItem) {
            mViewPager!!.setCurrentItem(position, true)
        }
        ToastUtils.showShort("position:$position")
    }
    private fun setupBanner(@APageStyle pageStyle: Int) {
        mViewPager
            ?.setPageMargin(resources.getDimensionPixelOffset(R.dimen.dp_15))
            ?.setRevealWidth(resources.getDimensionPixelOffset(R.dimen.dp_10))
            ?.setPageStyle(pageStyle)
            ?.create(getPicList(5))
    }

}