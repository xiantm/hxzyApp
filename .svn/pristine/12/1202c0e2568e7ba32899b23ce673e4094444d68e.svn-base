package hxzy.ptb.hxzyappkit.ui.main

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.alibaba.fastjson.JSON
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.UIData
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITabSegment
import hxzy.ptb.hxzyappkit.App
import hxzy.ptb.hxzyappkit.R
import hxzy.ptb.hxzyappkit.domain.AppModule
import hxzy.ptb.hxzyappkit.domain.User
import hxzy.ptb.hxzyappkit.repository.ModuleRepository
import hxzy.ptb.hxzyappkit.repository.UserRepository
import hxzy.ptb.hxzyappkit.ui.login.LoginActivity

//通过这种方式就不用写findViewById而直接写view的Id调用方法就行了
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {


    lateinit var mPresenter: MainContract.Presenter
    lateinit var headImg: AvatarImageView
    lateinit var headText: TextView
    val fragmentList = arrayListOf<MainFragment>()
    val titles = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPresenter(MainPresenter(UserRepository(), ModuleRepository(), this))
        mPresenter.start()
    }

    override fun initView() {
        mainTopBar.addLeftImageButton(R.mipmap.icon_user_white, R.id.topbar_left_user).setOnClickListener {
            if (!mainDrawer.isDrawerOpen(Gravity.START)) mainDrawer.openDrawer(Gravity.START)
        }
        val headerView = navView.getHeaderView(0)
        headImg = headerView.findViewById<AvatarImageView>(R.id.navHeaderAvatar)
        headText = headerView.findViewById<TextView>(R.id.navHeaderText)
        headerView.findViewById<TextView>(R.id.navHeaderVersion).text = "当前版本: ${AppUtils.getAppVersionName()}"
        headImg.setTextAndColorSeed("未登录", "未登录")
        //清除当前用户用户信息
        headImg.setOnClickListener {
            mPresenter.removeCurrentUser()
            headImg.setTextAndColorSeed("未登录", "未登录")
            headText.text = "点击头像登录"
            mainDrawer.closeDrawer(Gravity.START)
            goLoginPage()
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_version -> checkVersion(hand = true)
                else -> {
                }
            }
            return@setNavigationItemSelectedListener false
        }

    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun showModule(modules: List<AppModule>) {
        mainTabs.reset()//清空所有的模块
        modules.forEach {
            titles.add(it.name)
            val normalColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_6)
            val selectColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_blue)
            mainTabs.setDefaultNormalColor(normalColor)
            mainTabs.setDefaultSelectedColor(selectColor)
            //解决icon大小问题
            val iconShowSize = QMUIDisplayHelper.dp2px(this, 20)
            val drawable = BitmapDrawable(resources, it.bitmap_normal)
            drawable!!.setBounds(0, 0, iconShowSize, iconShowSize)
            val drawablea = BitmapDrawable(resources, it.bitmap_active)
            drawablea!!.setBounds(0, 0, iconShowSize, iconShowSize)
            val tab = QMUITabSegment.Tab(drawable, drawablea, it.name, false, false)
            //设置红点
            if (it.num > 0) tab.showSignCountView(this, it.num)
            mainTabs.addTab(tab)
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putString("modules", JSON.toJSONString(it.children))
            fragment.arguments = bundle
            fragmentList.add(fragment)
        }
        mainTabs.notifyDataChanged()//刷新
        mainViewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount() = fragmentList.size
            override fun getItem(position: Int): Fragment = fragmentList[position]
        }

        mainViewPager.visibility = View.VISIBLE
        mainTabs.setupWithViewPager(mainViewPager, false)
        //页面切换后设置标题
        mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }
            override fun onPageSelected(position: Int) {
                updateTitle(title = titles[position])
            }
        })
        if(titles.isNotEmpty()) updateTitle(title = titles[0])
    }

    override fun hideModule() {
        titles.clear()
        fragmentList.removeAll { true }
        mainViewPager.visibility = View.INVISIBLE
    }

    override fun updateTitle(title: String) {
        mainTopBar.setTitle(title)
    }


    override fun updateUserInfo(user: User) {
        headImg.setTextAndColorSeed(user.username, user.username)
        headText.text = "点击头像切换用户"
        mainDrawer.closeDrawer(Gravity.START)
        checkVersion()
    }

    override fun goLoginPage() = startActivityForResult(Intent(this, LoginActivity::class.java), 1)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 2) { //登录成功后更新页面
            App.currentUser?.let { updateUserInfo(it) }
            hideModule()
            mPresenter.getModule()
        }
    }

    //检查版本号
    override fun checkVersion(hand: Boolean) {
        val builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl("${App.serverIpPortDir}/mobile/checkVersion")
                .request(object : RequestVersionListener {
                    override fun onRequestVersionSuccess(result: String?): UIData? {
                        if (AppUtils.getAppVersionName() == result) {
                            if (hand) ToastUtils.showShort("程序已经是最新版了。")
                            return null
                        }
                        val ud = UIData.create()
                        ud.downloadUrl = "${App.serverIpPortDir}/apk/app.apk"
                        ud.title = "程序有新版本"
                        ud.content = "是否更新程序?"
                        return ud
                    }

                    override fun onRequestVersionFailure(message: String?) {
                        ToastUtils.showShort("请连接正确的WiFi")
                        LogUtils.e("无法获取版本数据。")
                    }

                })
        builder.isForceRedownload = true
        builder.excuteMission(this)
    }

}
