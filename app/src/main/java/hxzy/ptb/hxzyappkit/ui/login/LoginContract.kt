package hxzy.ptb.hxzyappkit.ui.login

import hxzy.ptb.hxzyappkit.BasePresenter
import hxzy.ptb.hxzyappkit.BaseView
import hxzy.ptb.hxzyappkit.domain.LoginHistory

interface LoginContract {

    interface View : BaseView<Presenter> {

        fun closePage(isLogin: Boolean)

        fun showLoading(isLoading: Boolean)

        fun setLoginHistory(loginHistories: List<LoginHistory>)

        fun initView()

    }

    interface Presenter : BasePresenter {

        fun login(account: String, password: String)

        fun getLoginHistory()


    }
}