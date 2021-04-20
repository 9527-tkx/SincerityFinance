package com.vf.sincerityfinance.utils

import android.content.Context
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vf.sincerityfinance.R

/**
 * @program: SincerityFinance
 *
 * @description:获取lottie素材
 *
 * @author: tkx
 *
 * @create: 2021-04-15 10:28
 **/
enum class LottieAnimation(val value: String) {


    HOME("shouye.json"),
    ME("wode.json"),
    PROJECT("yingyong.json")


}

val mNavigationAnimationList = arrayListOf(
    LottieAnimation.HOME,
    LottieAnimation.PROJECT,
    LottieAnimation.ME,
)

/**
 * @desc 动画对应选择器（好响亮的名字）
 * @param menuItem MenuItem
 * @return LottieAnimation
 */
fun select(menuItem: MenuItem): LottieAnimation {

    return when (menuItem.itemId) {
        R.id.navigation_home -> LottieAnimation.HOME
        R.id.navigation_dashboard -> LottieAnimation.PROJECT
        R.id.navigation_notifications -> LottieAnimation.ME

        else -> LottieAnimation.HOME
    }
}

/**
 * @desc 过滤掉当前选择item
 * @param menuItem MenuItem
 * @return Unit
 */
fun noselect(mainBottom: BottomNavigationView, menuItem: MenuItem) {
    when (menuItem.itemId) {
        R.id.navigation_home -> {
            mainBottom.menu.findItem(R.id.navigation_dashboard)
                .setIcon(R.mipmap.project_unalready)
            mainBottom.menu.findItem(R.id.navigation_notifications)
                .setIcon(R.mipmap.me_unalready)
        }
        R.id.navigation_dashboard -> {
            mainBottom.menu.findItem(R.id.navigation_home)
                .setIcon(R.mipmap.home_unalready)
            mainBottom.menu.findItem(R.id.navigation_notifications)
                .setIcon(R.mipmap.me_unalready)
        }
        R.id.navigation_notifications -> {
            mainBottom.menu.findItem(R.id.navigation_dashboard)
                .setIcon(R.mipmap.project_unalready)
            mainBottom.menu.findItem(R.id.navigation_home)
                .setIcon(R.mipmap.home_unalready)
        }


    }
}

/**
 * 获取 Lottie json 文件
 */
fun getLottieAnimationList(context: Context): ArrayList<LottieAnimation> {
    return mNavigationAnimationList

}