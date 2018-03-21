package hxzy.ptb.hxzyappkit.ui.login

import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import hxzy.ptb.hxzyappkit.App
import hxzy.ptb.hxzyappkit.repository.UserRepository

class LoginPresenter constructor(val view: LoginContract.View, val userRepository: UserRepository) : LoginContract.Presenter {
    val app = Utils.getApp() as App
    override fun login(account: String, password: String) {
        view.showLoading(true)
        userRepository.getUserByLogin(account, password, {
            if (it) view.closePage(isLogin = true)
            else ToastUtils.showShort("用户或密码错误")
            view.showLoading(false)
        }, {
            ToastUtils.showShort("请求失败,请检查网络")
            view.showLoading(false)
        })
    }

    override fun getLoginHistory() = app.diskIO.execute {
        userRepository.getLoginHistory().let { app.mainThread.execute { view.setLoginHistory(it) } }
    }

    override fun start() {
        view.setPresenter(this)
        view.initView()
        getLoginHistory()
    }
}