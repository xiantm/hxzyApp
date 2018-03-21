package hxzy.ptb.hxzyappkit.repository

import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.github.kittinunf.fuel.Fuel
import hxzy.ptb.hxzyappkit.App
import hxzy.ptb.hxzyappkit.domain.LoginHistory
import hxzy.ptb.hxzyappkit.domain.AppModule
import hxzy.ptb.hxzyappkit.domain.User

const val CURRENT_USER_INFO = "CURRENT_USER_INFO"
//const val LOGIN_HISTORY = "LOGIN_HISTORY"

/**
 * Created by xiantm on 2018/2/28.
 */
class UserRepository {

    private val app = Utils.getApp() as App
    private val historyDao = app.db.loginHistoryDao()
    private val sp = SPUtils.getInstance()

    //获取当前用户信息
    fun getUserInfo(): User? {
        val userStr = sp.getString(CURRENT_USER_INFO)
        if (!StringUtils.isEmpty(userStr)) {
            return JSON.parseObject(userStr, User::class.java)
        }
        return null
    }

    //移除当前用户信息
    fun clearUserInfo() {
        sp.remove(CURRENT_USER_INFO)
        App.currentUser = null
    }

    //保存当前用户信息
    fun saveUserInfo(user: User) {
        sp.put(CURRENT_USER_INFO, JSON.toJSONString(user))
        App.currentUser = user
    }

    /**
     * 登录
     * @param account 账号
     * @param password 密码
     */
    fun getUserByLogin(account: String, password: String, success: (Boolean) -> Unit, failure: () -> Unit) {
        Fuel.post("${App.serverIpPortDir}/mobile/login", listOf("account" to account, "password" to password)).timeout(1000).responseString { _, _, result ->
            result.fold({ d ->
                val user = JSON.parseObject(d, User::class.java)
                user.let {
                    if ("-1" != it.id) { //用户Id为-1表示为查询出用户
                        saveUserInfo(user)
                        app.diskIO.execute { saveLoginHistory(account, password) }
                        success(true)
                    } else {
                        success(false)
                    }
                }
            }, { err ->
                err.printStackTrace(System.err)
                when (err.response.statusCode) {
                    405 -> ToastUtils.showShort("请求放法错误")
                }
                failure()
            })
        }
    }


    //存入登录历史
    fun saveLoginHistory(account: String, password: String) {

        historyDao.insertLoginHistory(LoginHistory(account, password))
//        val historyStr = sp.getString(LOGIN_HISTORY)
//        val history = if (historyStr.isNullOrBlank()) arrayListOf<LoginHistory>() else JSON.parseArray(historyStr, LoginHistory::class.java)
//        history.forEach { if (it.account == account) return@saveLoginHistory }
//        history.add(LoginHistory(account, password))
//        sp.put(LOGIN_HISTORY, JSON.toJSONString(history))
    }



    //获取登录历史
    fun getLoginHistory() = historyDao.loginHistory
}