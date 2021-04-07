package com.vf.sincerityfinance.ui.fragment.login

import android.os.Bundle
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.vf.sincerityfinance.R
import com.vf.sincerityfinance.base.BaseFragment
import com.vf.sincerityfinance.databinding.FragmentLoginBinding
import com.vf.sincerityfinance.utils.CacheUtil
import com.vf.sincerityfinance.viewmodel.appViewModel
import com.vf.sincerityfinance.viewmodel.vm.LoginRegisterViewModel
import com.vf.sincerityfinance.viewmodel.request.RequestLoginRegisterViewModel
import com.vf.sincerityfinance.weight.ext.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.ext.parseState

/**
 * @program: SincerityFinance
 *
 * @description:登陆页面
 *
 * @author: tkx
 *
 * @create: 2021-04-01 18:35
 **/
class LoginFragment : BaseFragment<LoginRegisterViewModel, FragmentLoginBinding>() {

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_login

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.viewmodel = mViewModel
        mDatabind.click = ProxyClick()
        toolbar.initClose("注册") {
            nav().navigateUp()
        }
    }



    override fun createObserver() {

        requestLoginRegisterViewModel.loginResult.observe(viewLifecycleOwner, Observer { resultState ->
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

    inner class ProxyClick {

        fun clear() {
            mViewModel.username.set("")
        }

        fun login() {
            when {
                mViewModel.username.get().isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                else -> requestLoginRegisterViewModel.loginReq(
                    mViewModel.username.get(),
                    mViewModel.password.get()
                )
            }
        }

        fun goRegister() {
            hideSoftKeyboard(activity)
            nav().navigateAction(R.id.action_loginFragment_to_registerFrgment)
        }

        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.set(isChecked)
            }
    }
}