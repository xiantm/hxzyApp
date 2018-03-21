package hxzy.ptb.hxzyappkit.ui.page1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hxzy.ptb.hxzyappkit.R
import kotlinx.android.synthetic.main.activity_page1.*

class Page1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page1)
        initViews()
    }

    private fun initViews() {
        page1TopBar.setTitle("页面一")
        page1TopBar.addLeftBackImageButton().setOnClickListener { onBackPressed() }
    }
}
