package hxzy.ptb.hxzyappkit

import android.app.Application
import android.arch.persistence.room.Room
import com.blankj.utilcode.util.Utils
import hxzy.ptb.hxzyappkit.db.AppDatabase
import hxzy.ptb.hxzyappkit.domain.User
import java.util.concurrent.Executor

/**
 * 应用程序入口,一些初始化工作需要放在这里
 */
class App : Application() {

    lateinit var db: AppDatabase

    lateinit var diskIO: Executor

    lateinit var networkIO: Executor

    lateinit var mainThread: Executor

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "hxzy.db").build()
        val appExecutors = AppExecutors()
        diskIO = appExecutors.diskIO()
        networkIO = appExecutors.networkIO()
        mainThread = appExecutors.mainThread()
        //初始化工具代码框架,Toast之类的需要context
        Utils.init(this)
    }


    companion object {

        //快速获取当前用户信息
        @JvmStatic
        var currentUser: User? = null

        @JvmStatic
        val serverIpPortDir = "http://192.168.2.1:9090/tlms"
    }
}