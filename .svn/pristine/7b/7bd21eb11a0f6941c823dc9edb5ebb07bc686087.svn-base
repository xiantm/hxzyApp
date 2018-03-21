package hxzy.ptb.hxzyappkit.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.fastjson.JSON
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hxzy.ptb.hxzyappkit.App
import hxzy.ptb.hxzyappkit.R
import hxzy.ptb.hxzyappkit.domain.AppModule
import hxzy.ptb.hxzyappkit.ui.page1.Page1Activity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_main, null)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        val modules = JSON.parseArray(arguments!!.getString("modules"), AppModule::class.java)
        val spanCount = 4 //每行显示四个模块
        mainModuleList.layoutManager = GridLayoutManager(context, spanCount)!!
        //设置右\下边线
        mainModuleList.addItemDecoration(GridDividerItemDecoration(context, spanCount))
        val adapter = object : BaseQuickAdapter<AppModule, BaseViewHolder>(R.layout.item_module, modules) {
            override fun convert(helper: BaseViewHolder?, item: AppModule?) {
                item?.let { module ->
                    helper?.let {
                        it.setText(R.id.mainModuleText, module.name)
                        //设置模块工作数
                        if(module.num > 0){
                            it.getView<View>(R.id.mainModuleNL).visibility = View.VISIBLE
                            it.setText(R.id.mainModuleNum,module.num.toString())
                        }
                        //加载模块图片
                        Glide.with(this@MainFragment)
                                .load("${App.serverIpPortDir}/static/img/appModule/${module.image_normal}")
                                .into(it.getView<ImageView>(R.id.mainModuleImg))
                    }
                }

            }
        }

        adapter.setOnItemClickListener{
            adapter, _, position ->
            val module = adapter.getItem(position) as AppModule
            //todo 在这里配置跳转页面
            when (module.name) {
                "1-2" -> startActivity(Intent(context, Page1Activity::class.java))
            }
        }

        mainModuleList.adapter = adapter
    }


}