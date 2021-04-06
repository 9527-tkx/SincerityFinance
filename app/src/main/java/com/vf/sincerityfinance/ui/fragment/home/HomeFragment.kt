package com.vf.sincerityfinance.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.kingja.loadsir.core.LoadService
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentHomeBinding
import com.vf.sincerityfinance.viewmodel.request.RequestHomeViewModel
import com.vf.sincerityfinance.viewmodel.vm.HomeViewModel
import com.vf.sincerityfinance.weight.ext.*
import com.vf.sincerityfinance.weight.recyclerView.DefineLoadMoreView
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.include_recyclerview.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/27
 * 描述　:
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

//    适配器
//    private val articleAdapter: AriticleAdapter by lazy { AriticleAdapter(arrayListOf(), true) }

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    //recyclerview的底部加载view 因为在首页要动态改变他的颜色，所以加了他这个字段
    private lateinit var footView: DefineLoadMoreView


    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            requestHomeViewModel.getBannerData()
            requestHomeViewModel.getHomeData(true)
        }
        //初始化 toolbar
        toolbar.run {
            init("首页")
            inflateMenu(R.menu.home_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.home_search -> {
//                        nav().navigateAction(R.id.action_mainfragment_to_searchFragment)
                    }
                }
                true
            }
        }


                }




    /**
     * 懒加载
     */
    override fun lazyLoadData() {
        //设置界面 加载中
        loadsir.showLoading()
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