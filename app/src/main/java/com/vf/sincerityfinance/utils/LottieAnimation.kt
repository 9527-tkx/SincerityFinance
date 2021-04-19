package com.vf.sincerityfinance.utils

import android.content.Context

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
 * 获取 Lottie json 文件
 */
fun getLottieAnimationList(context: Context): ArrayList<LottieAnimation> {
    return mNavigationAnimationList

}