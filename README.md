**诚意财务app**
基于kotlin mvvm框架 用到了viewbinding databinding lifecycle 基于livedata封装的UnPeekLiveData（解决了livedata存在重返二级页面发生数据无变化的情况，暂且叫它数据倒灌） 缓存用的mmkv 网络请求用的retrofit+协程 整体导航使用jetpack的nav 跳转更清晰
使用了大量的kotlin扩展函数 以及相关的第三方框架也在gradle中做了详细注释

新版本的viewbinding是为了解决kotlin利用id直接获取控件会出现利用混乱的问题 现在kotlin已经弃用了（但是还能用） 代替方式就是viewbinding

开启方式与databinding一样
buildFeatures {
        dataBinding = true
        viewBinding = true
    }

程序的入口为mainactivity全局只有一个activity（当然需要的话可以写个欢迎页activity）可以在res-navigation-main_navation.xml中查看整体的navigation
添加新的fragment需要在此xml文件下进行注册注册方式同下，然后写相关的action 在activity或者fragment中调用nav()（已经写过相关的扩展函数直接用就行）
有一个很方便的点 想找哪个fragment直接用nav的可视化界面就行
比如从首页跳到搜索页
btn_already.onclick {
     nav().navigateAction(R.id.action_mainfragment_to_searchFragment)
}
返回上一个fragment调用nav().navigatup

<fragment
        android:id="@+id/xxxxxFragment"
        android:name="相对路径"
        android:label="别名" />

LoadService一个优雅的处理加载反馈的三方框架（搭配网络请求食用更佳） 传送门：https://github.com/KingJA/LoadSir
扩展函数的概念可以去了解下 ext包下的都是扩展函数 一般将扩展函数声明成顶层函数 用的时候直接拿即可 相当于工具类 但是更简洁干净 对Android开发真的十分有用
相关kotlin函数的问题（lambda是kotlin很厉害的一个点） 可以看我的最新博客 《是时候整理kotlin的函数了！》 传送门：https://blog.csdn.net/qq_42945340/article/details/115674127?spm=1001.2014.3001.5501
用的mvvm框架不同的是vm层分为requestviewmodel与viewmodel 职责更细化 requestviewmodel 只做一件事，拿数据源 viewmodel也只做一件事处理databinding 绑定界面需要的数据（虽然有点麻烦，但是东西多了好找 大橘观！！！）
livedata：在底层数据更改的时候通知界面 重写basefragment中的createObserver()方法 把对应的requestviewmodel中所有的MutableLiveData<ResultState<UserInfo>>（泛型指的是相关的bean对象）全给写个监听 相关的mmkv处理本地缓存也写在里面以登入为例子
  override fun createObserver() {
         requestLoginRegisterViewModel.loginResult.observe(viewLifecycleOwner,Observer { resultState ->
                 parseState(resultState, {
                     //登录成功 通知账户数据发生改变鸟
                     CacheUtil.setUser(it)
                     CacheUtil.setIsLogin(true)
                     appViewModel.userInfo.value = it
                     nav().navigateUp()
                 }, {
                     //登录失败
                     showMessage(it.errorMsg)
                 })
             })
     }
用了这么多fragment懒加载的问题肯定得处理 在fragment中重写这个就行 网络请求先在这里调一遍
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

然后界面大部分都是小帅最讨厌的约束布局 v我 我来改ok 由于kotlin和java可以无缝衔接的原因 后续的网络请求 可以沿用之前的也可以按着这个来这是协程+retrofit反正都是retrofit就是协程和线程的区别
协程就是单线程语言实现多线程的方式js 啊 python都有 可以用同步的方式写异步请求 看个一天也能看懂 反正说来说去就是因为我没看懂rxjava 还有哪里不清晰的看这个github也行 大部分都是基于它的 https://github.com/hegaojian/JetpackMvvm
因为ui也是后来才定下来的基本这周我只画图和绑定navigation 接口方面一时半会儿是出不来了 然后最近也在忙着面试 后续要是决定继续用协程我会尽可能帮忙

当初因感情与热爱来到这 未曾嫌我才疏学浅 承蒙同事厚爱 倍敢至亲 至今心怀感念 事到如今 事与愿违 奋不顾身已经用完了 剩下的只有三思而后行

总之 告辞了各位


