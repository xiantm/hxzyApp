package hxzy.ptb.hxzyappkit.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.Utils
import com.github.kittinunf.fuel.Fuel
import hxzy.ptb.hxzyappkit.App
import hxzy.ptb.hxzyappkit.R
import hxzy.ptb.hxzyappkit.domain.AppModule
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by xiantm on 18-3-14.
 */
class ModuleRepository {

    private val app = Utils.getApp() as App
    //获取用户模块权限信息
    fun getModules(id: String, success: (List<AppModule>) -> Unit, failure: () -> Unit) {
        Fuel.get("${App.serverIpPortDir}/mobile/getModule", listOf("id" to id)).timeout(1000).responseString { _, _, result ->
            result.fold({ d ->
                success(JSON.parseArray(d, AppModule::class.java))
            }, { err ->
                err.printStackTrace(System.err)
                failure()
            })
        }
    }

    fun getRootImg(modules: List<AppModule>, callback: (List<AppModule>) -> Unit) {

        //获取图片
        fun loadImg(imageName: String, active: Boolean, loaded: (Bitmap) -> Unit) {
            val url = URL("${App.serverIpPortDir}/static/img/appModule/${imageName}")
            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 5000
            conn.requestMethod = "GET"
            if (conn.responseCode == 200) {
                val inputStream = conn.inputStream
                val res = BitmapFactory.decodeStream(inputStream)
                loaded(res)
            } else {
                if (active) loaded(BitmapFactory.decodeResource(app.resources, R.mipmap._404_blue))
                else loaded(BitmapFactory.decodeResource(app.resources, R.mipmap._404))
            }
        }
        app.networkIO.execute {
            modules.forEach { item ->
                item.image_normal?.let {
                    loadImg(it, false) { item.bitmap_normal = it }
                } ?: item.setBitmap_normal(BitmapFactory.decodeResource(app.resources, R.mipmap._404))
                item.image_active?.let {
                    loadImg(it, false) { item.bitmap_active = it }
                } ?: item.setBitmap_active(BitmapFactory.decodeResource(app.resources, R.mipmap._404_blue))
            }
            app.mainThread.execute { callback(modules) }
        }

    }

}