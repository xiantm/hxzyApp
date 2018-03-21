package hxzy.ptb.hxzyappkit.ui.main

import hxzy.ptb.hxzyappkit.BasePresenter
import hxzy.ptb.hxzyappkit.BaseView
import hxzy.ptb.hxzyappkit.domain.AppModule
import hxzy.ptb.hxzyappkit.domain.User

/**
 * 方法声明类，方便页面逻辑设计
 */
interface MainContract {

    interface View : BaseView<Presenter>{

        fun initView()

        //根据配置显示模块
        fun showModule(modules: List<AppModule>)

        //隐藏模块信息
        fun hideModule()

        //更新标题
        fun updateTitle(title: String)

        //设置用户信息
        fun updateUserInfo(user: User)

        //跳转到登录页面
        fun goLoginPage()

        fun checkVersion(hand: Boolean = false)
    }


    interface Presenter: BasePresenter{

        //获取当前用户信息
        fun getCurrentUser()

        fun removeCurrentUser()

        fun getModule()

        fun getRootImg(modules: List<AppModule>)
    }
}