package com.vf.sincerityfinance.ui.fragment.home

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.kingja.loadsir.core.LoadService
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentHomeBinding
import com.vf.sincerityfinance.viewmodel.request.RequestHomeViewModel
import com.vf.sincerityfinance.viewmodel.vm.HomeViewModel
import com.vf.sincerityfinance.weight.ext.*
import com.vf.sincerityfinance.weight.recyclerView.DefineLoadMoreView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_recyclerview.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.ext.view.invisible

/**
 * 作者　: tkx
 * 时间　: 2021/4/8
 * 描述　: 首页fragment
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {


    //界面状态管理者
//    private lateinit var loadsir: LoadService<Any>

    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        QMUIStatusBarHelper.translucent(this.activity)
        QMUIStatusBarHelper.setStatusBarLightMode(this.activity)

        cl_more.run {
            onClick {
                nav().navigateAction(R.id.action_navigation_home_to_morenoticeFragment)
            }
        }
        rl_information.run {
            onClick {
                nav().navigateAction(R.id.action_navigation_home_to_informationFragment)
            }
        }
        rl_taxreturn.run {
            onClick {
                nav().navigateAction(R.id.action_navigation_home_to_taxreturnsFragment)
            }
        }


    }


    /**
     * 懒加载
     */
    override fun lazyLoadData() {
        //设置界面 加载中
//        loadsir.showLoading()
        //请求轮播图数据
        requestHomeViewModel.getBannerData()
        //请求文章列表数据
        requestHomeViewModel.getHomeData(true)
    }

    override fun createObserver() {
        requestHomeViewModel.run {
            //监听首页文章列表请求的数据变化

        }


    }

}