package com.vf.sincerityfinance.utils

import android.view.MenuItem
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @program: SincerityFinance
 *
 * @description: 图标动画
 *
 * @author: tkx
 *
 * @create: 2021-04-15 10:25
 **/
object AnimationUtil {

    /**
     * @dec 动态获取 Lottie Drawable
     * @param menuItem MenuItem
     * @param bottomNavigationView BottomNavigationView
     * @param selector Function1<MenuItem, LottieAnimation>
     * @return LottieDrawable
     */
    fun getLottieDrawable(
        menuItem: MenuItem,
        bottomNavigationView: BottomNavigationView,
        selector: (MenuItem) -> LottieAnimation
    ): LottieDrawable {
        return LottieDrawable().apply {
            val result = LottieCompositionFactory.fromAssetSync(
                bottomNavigationView.context.applicationContext,
                selector(menuItem).value
            )
            callback = bottomNavigationView
            composition = result.value
        }
    }





}