package hxzy.ptb.hxzyappkit.ui.main

import com.blankj.utilcode.util.ToastUtils
import hxzy.ptb.hxzyappkit.domain.AppModule
import hxzy.ptb.hxzyappkit.domain.User
import hxzy.ptb.hxzyappkit.repository.ModuleRepository
import hxzy.ptb.hxzyappkit.repository.UserRepository


class MainPresenter constructor(val userRepository: UserRepository, val moduleRepository: ModuleRepository, val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        //设置桥梁
        view.setPresenter(this)
        //初始化视图
        view.initView()
        //初始化数据
        view.checkVersion()
        getCurrentUser()
    }

    override fun getCurrentUser() {
        userRepository.getUserInfo()?.let {
            view.updateUserInfo(it)
            getModule()
        } ?: view.goLoginPage()
    }

    override fun getModule() {
        moduleRepository.getModules(userRepository.getUserInfo()!!.id, { all ->
            val roots = all.filter { it.parentId.isNullOrBlank() }
            roots.forEach { item ->
                item.children = arrayListOf()
                all.forEach { if (it.parentId == item.id) item.children.add(it) }
                item.children.sortBy { it.ordernum }
            }
            roots.sortedBy { it.ordernum }
            getRootImg(roots)
        }, {
            ToastUtils.showShort("网络请求出错，试试检查网络")
        })
    }

    override fun removeCurrentUser() {
        userRepository.clearUserInfo()
    }

    override fun getRootImg(modules: List<AppModule>) {
        moduleRepository.getRootImg(modules) { view.showModule(modules = it) }
    }
}