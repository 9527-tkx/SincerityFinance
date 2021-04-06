package com.vf.sincerityfinance.viewmodel.vm

import com.vf.sincerityfinance.utils.ColorUtil
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.databind.IntObservableField
import me.hgj.jetpackmvvm.callback.databind.StringObservableField
import me.hgj.jetpackmvvm.callback.livedata.UnPeekLiveData

/**
 * @program: SincerityFinance
 *
 * @description: 专门存放 MeFragment 界面数据的ViewModel
 *
 * @author: tkx
 *
 * @create: 2021-04-06 17:01
 **/
class MeViewModel : BaseViewModel() {
    var name = StringObservableField("请先登录~")

    var integral = IntObservableField(0)

    var info = StringObservableField("id：--　排名：-")

    var imageUrl = StringObservableField(ColorUtil.randomImage())

    var testString = UnPeekLiveData<String>()
}