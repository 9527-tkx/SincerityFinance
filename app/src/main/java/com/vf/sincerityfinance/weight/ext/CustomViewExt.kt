package com.vf.sincerityfinance.weight.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.view.MenuItem
import androidx.navigation.ui.NavigationUI
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.adapter.ViewBindingSampleAdapter
import com.vf.sincerityfinance.utils.AnimationUtil
import com.vf.sincerityfinance.utils.SettingUtil
import com.vf.sincerityfinance.utils.noselect
import com.vf.sincerityfinance.utils.select
import com.vf.sincerityfinance.weight.recyclerView.DefineLoadMoreView
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.indicator.enums.IndicatorSlideMode
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.LoadingCallback
import me.hgj.jetpackmvvm.ext.util.toHtml


/**
 * ?????????: hegaojian
 * ?????????: 2020/2/20
 * ?????????:????????????????????????????????????
 */


fun LoadService<*>.setErrorText(message: String) {
    if (message.isNotEmpty()) {
        this.setCallBack(ErrorCallback::class.java) { _, view ->
            view.findViewById<TextView>(R.id.error_text).text = message
        }
    }
}

/**
 * ??????????????????
 * @param message ?????????????????????????????????
 */
fun LoadService<*>.showError(message: String = "") {
    this.setErrorText(message)
    this.showCallback(ErrorCallback::class.java)
}

/**
 * ???????????????
 */
fun LoadService<*>.showEmpty() {
    this.showCallback(EmptyCallback::class.java)
}

/**
 * ???????????????
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}

fun loadServiceInit(view: View, callback: () -> Unit): LoadService<Any> {
    val loadsir = LoadSir.getDefault().register(view) {
        //??????????????????????????????
        callback.invoke()
    }
    loadsir.showSuccess()
    SettingUtil.setLoadingColor(SettingUtil.getColor(appContext), loadsir)
    return loadsir
}

//???????????????Recyclerview
fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): RecyclerView {
    layoutManager = layoutManger
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

//??????SwipeRecyclerView
fun SwipeRecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): SwipeRecyclerView {
    layoutManager = layoutManger
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

fun SwipeRecyclerView.initFooter(loadmoreListener: SwipeRecyclerView.LoadMoreListener): DefineLoadMoreView {
    val footerView = DefineLoadMoreView(appContext)
    //?????????????????????
    footerView.setLoadViewColor(SettingUtil.getOneColorStateList(appContext))
    //????????????????????????
    footerView.setmLoadMoreListener(SwipeRecyclerView.LoadMoreListener {
        footerView.onLoading()
        loadmoreListener.onLoadMore()
    })
    this.run {
        //????????????????????????
        addFooterView(footerView)
        setLoadMoreView(footerView)
        //????????????????????????
        setLoadMoreListener(loadmoreListener)
    }
    return footerView
}

/**
 * @des banner ???????????????????????? ???Int?????????
 * * @receiver BannerViewPager<Int>
 * @param int Int
 * @param mViewPager BannerViewPager<Int>
 * @param list List<T>
 * @param lifecycleRegistry LifecycleRegistry
 * @return Unit
 */
fun BannerViewPager<Int>.init(
    int: Int,
    mViewPager: BannerViewPager<Int>,
    lifecycleRegistry: LifecycleRegistry
) {
    with(mViewPager) {
        setIndicatorSlideMode(IndicatorSlideMode.WORM)
        setIndicatorSliderColor(
            resources.getColor(R.color.gray_1),
            resources.getColor(R.color.tkx_like)
        )
        setIndicatorStyle(1)
        setIndicatorSliderRadius(
            resources.getDimensionPixelOffset(R.dimen.dp_4),
            resources.getDimensionPixelOffset(R.dimen.dp_4)
        )
        setLifecycleRegistry(lifecycleRegistry)
//            .setOnPageClickListener { view: View, position: Int ->
//                pageClick(
//                    view,
//                    position
//                )
//            }
        setAdapter(ViewBindingSampleAdapter(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        setInterval(5000)
        //??????banner???????????????pagestyle?????? ????????????

        setPageMargin(resources.getDimensionPixelOffset(R.dimen.dp_15))
        setRevealWidth(resources.getDimensionPixelOffset(R.dimen.dp_10))
        setPageStyle(int)
        create(SettingUtil.getPicList(5))


    }
}

///**
// * banner????????????????????????
// * @param view View
// * @param position Int
// * @return Unit
// */
//private fun pageClick(view: View, position: Int) {
//    if (position != mViewPager!!.currentItem) {
//        mViewPager!!.setCurrentItem(position, true)
//    }
//    ToastUtils.showShort("position:$position")
//}


fun RecyclerView.initFloatBtn(floatbtn: FloatingActionButton) {
    //??????recyclerview?????????????????????????????????????????????????????????????????????
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        @SuppressLint("RestrictedApi")
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!canScrollVertically(-1)) {
                floatbtn.visibility = View.INVISIBLE
            }
        }
    })
    floatbtn.backgroundTintList = SettingUtil.getOneColorStateList(appContext)
    floatbtn.setOnClickListener {
        val layoutManager = layoutManager as LinearLayoutManager
        //????????????recyclerview ?????????????????????????????????????????????40????????????????????????????????????????????????????????????????????????
        if (layoutManager.findLastVisibleItemPosition() >= 40) {
            scrollToPosition(0)//?????????????????????????????????(??????)
        } else {
            smoothScrollToPosition(0)//??????????????????????????????(?????????)
        }
    }
}

////????????? SwipeRefreshLayout
//fun SwipeRefreshLayout.init(onRefreshListener: () -> Unit) {
//    this.run {
//        setOnRefreshListener {
//            onRefreshListener.invoke()
//        }
//        //??????????????????
//        setColorSchemeColors(SettingUtil.getColor(appContext))
//    }
//}

/**
 * ??????????????????toolbar ???????????????
 */
fun Toolbar.init(titleStr: String = ""): Toolbar {
    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr
    return this
}

/**
 * ????????????????????????toolbar
 */
fun Toolbar.initClose(
    titleStr: String = "",
    backImg: Int = R.mipmap.ic_back,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr.toHtml()
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

/**
 * ????????????????????????????????????????????????????????????????????? ????????????????????????????????????????????? Textview???FragmentLayout???????????????????????????
 * ???????????????BottomNavigationViewEx????????????????????????FragmentLayout???????????? is Fragmentlayout????????? is BottomNavigationViewEx??????
 * ??????????????????????????? is FragmentLayout???????????? ?????? is BottomNavigationViewEx???????????????
 */
fun setUiTheme(color: Int, vararg anyList: Any?) {
    anyList.forEach { view ->
        view?.let {
            when (it) {
                is LoadService<*> -> SettingUtil.setLoadingColor(color, it as LoadService<Any>)
                is FloatingActionButton -> it.backgroundTintList =
                    SettingUtil.getOneColorStateList(color)
                is SwipeRefreshLayout -> it.setColorSchemeColors(color)
                is DefineLoadMoreView -> it.setLoadViewColor(SettingUtil.getOneColorStateList(color))
                is BottomNavigationViewEx -> {
                    it.itemIconTintList = SettingUtil.getColorStateList(color)
                    it.itemTextColor = SettingUtil.getColorStateList(color)
                }
                is Toolbar -> it.setBackgroundColor(color)
                is TextView -> it.setTextColor(color)
                is LinearLayout -> it.setBackgroundColor(color)
                is ConstraintLayout -> it.setBackgroundColor(color)
                is FrameLayout -> it.setBackgroundColor(color)
            }
        }
    }
}

//??????????????????????????????
//fun BaseQuickAdapter<*, *>.setAdapterAnimation(mode: Int) {
//    //??????0????????????????????? ????????????
//    if (mode == 0) {
//        this.animationEnable = false
//    } else {
//        this.animationEnable = true
//        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[mode - 1])
//    }
//}

//fun MagicIndicator.bindViewPager2(
//    viewPager: ViewPager2,
//    mStringList: List<String> = arrayListOf(),
//    action: (index: Int) -> Unit = {}) {
//    val commonNavigator = CommonNavigator(appContext)
//    commonNavigator.adapter = object : CommonNavigatorAdapter() {
//
//        override fun getCount(): Int {
//            return  mStringList.size
//        }
//        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
//            return ScaleTransitionPagerTitleView(appContext).apply {
//                //????????????
//                text = mStringList[index].toHtml()
//                //????????????
//                textSize = 17f
//                //???????????????
//                normalColor = Color.WHITE
//                //????????????
//                selectedColor = Color.WHITE
//                //????????????
//                setOnClickListener {
//                    viewPager.currentItem = index
//                    action.invoke(index)
//                }
//            }
//        }
//        override fun getIndicator(context: Context): IPagerIndicator {
//            return LinePagerIndicator(context).apply {
//                mode = LinePagerIndicator.MODE_EXACTLY
//                //??????????????????
//                lineHeight = UIUtil.dip2px(appContext, 3.0).toFloat()
//                lineWidth = UIUtil.dip2px(appContext, 30.0).toFloat()
//                //???????????????
//                roundRadius = UIUtil.dip2px(appContext, 6.0).toFloat()
//                startInterpolator = AccelerateInterpolator()
//                endInterpolator = DecelerateInterpolator(2.0f)
//                //???????????????
//                setColors(Color.WHITE)
//            }
//        }
//    }
//    this.navigator = commonNavigator
//
//    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//        override fun onPageSelected(position: Int) {
//            super.onPageSelected(position)
//            this@bindViewPager2.onPageSelected(position)
//            action.invoke(position)
//        }
//
//        override fun onPageScrolled(
//            position: Int,
//            positionOffset: Float,
//            positionOffsetPixels: Int
//        ) {
//            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//            this@bindViewPager2.onPageScrolled(position, positionOffset, positionOffsetPixels)
//        }
//
//        override fun onPageScrollStateChanged(state: Int) {
//            super.onPageScrollStateChanged(state)
//            this@bindViewPager2.onPageScrollStateChanged(state)
//        }
//    })
//}

fun ViewPager2.init(
    fragment: Fragment,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    //???????????????
    this.isUserInputEnabled = isUserInputEnabled
    //???????????????
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}

//fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
//    //???????????????
//    this.isUserInputEnabled = false
//    this.offscreenPageLimit = 3
//    //???????????????
//    adapter = object : FragmentStateAdapter(fragment) {
//        override fun createFragment(position: Int): Fragment {
//            when (position) {
//                0 -> {
//                    return HomeFragment()
//                }
//                1 -> {
//                    return ProjectFragment()
//                }
//                2 -> {
//                    return MeFragment()
//                }
//                else -> {
//                    return HomeFragment()
//                }
//            }
//        }
//
//        override fun getItemCount() = 3
//    }
//    return this
//}
/**
 * @dec ??????tkx???????????????(??????????????????????????????????????? )
 * @receiver BottomNavigationView
 * @param listener OnNavigationItemSelectedListener
 * @param nav NavController
 * @return BottomNavigationView
 */
fun BottomNavigationView.init(navController: NavController, vararg ids: Int): BottomNavigationView {
    itemIconTintList = SettingUtil.getColorStateList(resources.getColor(R.color.qmui_config_color_gray_6))
    itemTextColor = SettingUtil.getColorStateList(resources.getColor(R.color.colorAccent))
    setupWithNavController(navController)
    setOnNavigationItemReselectedListener { }
    // ??????item???????????????????????????fragment??????????????????
    setOnNavigationItemSelectedListener {
        it.icon = AnimationUtil.getLottieDrawable(it, this, ::select)
        noselect(this, it)
        var icon = it.icon as? LottieDrawable
        icon?.apply {
            playAnimation()
        }
        //??????????????????fragment
        if (it.isChecked) {
            true
        }
        // ??????B?????????A????????????
        val popBackStack = navController.popBackStack(it.itemId, false)
        if (popBackStack) {
            // ?????????
            popBackStack
        } else {
            // ?????????
            NavigationUI.onNavDestinationSelected(
                it, navController
            )
        }
    }
    //??????BottomNavigation???????????? ?????????????????????Toast ---- ?????????????????????????????????bug
    val BottomNavigationMenuView: ViewGroup = (this.getChildAt(0) as ViewGroup)
    for (index in ids.indices) {
        BottomNavigationMenuView.getChildAt(index).findViewById<View>(ids[index])
            .setOnLongClickListener {
                true
            }
    }

    return this
}



/**
 * ???????????????
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}

/**
 * ??????????????????
 */
//fun <T> loadListData(
//    data: ListDataUiState<T>,
//    baseQuickAdapter: BaseQuickAdapter<T, *>,
//    loadService: LoadService<*>,
//    recyclerView: SwipeRecyclerView,
//    swipeRefreshLayout: SwipeRefreshLayout
//) {
//    swipeRefreshLayout.isRefreshing = false
//    recyclerView.loadMoreFinish(data.isEmpty, data.hasMore)
//    if (data.isSuccess) {
//        //??????
//        when {
//            //???????????????????????? ?????????????????????
//            data.isFirstEmpty -> {
//                loadService.showEmpty()
//            }
//            //????????????
//            data.isRefresh -> {
//                baseQuickAdapter.setList(data.listData)
//                loadService.showSuccess()
//            }
//            //???????????????
//            else -> {
//                baseQuickAdapter.addData(data.listData)
//                loadService.showSuccess()
//            }
//        }
//    } else {
//        //??????
//        if (data.isRefresh) {
//            //??????????????????????????????????????????????????????????????????
//            loadService.showError(data.errMessage)
//        } else {
//            recyclerView.loadMoreError(0, data.errMessage)
//        }
//    }
//}