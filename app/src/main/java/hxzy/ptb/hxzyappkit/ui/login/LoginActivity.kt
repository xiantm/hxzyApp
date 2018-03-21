package hxzy.ptb.hxzyappkit.ui.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import hxzy.ptb.hxzyappkit.R
import hxzy.ptb.hxzyappkit.domain.LoginHistory
import hxzy.ptb.hxzyappkit.repository.UserRepository
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var mPresenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setPresenter(LoginPresenter(this, UserRepository()))
        mPresenter.start()
    }

    override fun initView() {
        loginTopBar.addLeftBackImageButton().setOnClickListener { onBackPressed() }
        loginTopBar.setTitle("账号登录")

        tvPassword.setOnEditorActionListener { _: TextView?, actonId, _: KeyEvent? ->
            loginAction()
            return@setOnEditorActionListener true
        }
        logBtn.setOnClickListener { loginAction() }
        tvUserName.setOnItemClickListener { adapterView, view, i, l ->
            val user = adapterView.adapter.getItem(i) as LoginHistory
            tvPassword.setText(user.password)
        }
    }


    fun loginAction() {
        //重置错误信息
        inputUserName.error = null
        inputPassword.error = null
        val userName = tvUserName.text.toString()
        val password = tvPassword.text.toString()

        var focusView: View? = null

        if (TextUtils.isEmpty(userName)) {
            focusView = tvUserName
            inputUserName.error = "登录名不能为空!"
        }
        if (TextUtils.isEmpty(password)) {
            focusView = tvPassword
            inputPassword.error = "密码不能为空!"
        }
        focusView?.let {
            it.requestFocus()
            return@loginAction
        }
        mPresenter.login(userName,password)
    }

    override fun closePage(isLogin: Boolean) {
        if (isLogin) setResult(2)
        finish()
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressLogin.visibility = View.VISIBLE
            loginForm.visibility = View.INVISIBLE
        } else {
            progressLogin.visibility = View.GONE
            loginForm.visibility = View.VISIBLE
        }
    }

    override fun setLoginHistory(loginHistories: List<LoginHistory>) {
        loginHistories?.let {
            tvUserName.setAdapter(ArrayAdapter<LoginHistory>(this@LoginActivity, android.R.layout.simple_dropdown_item_1line, loginHistories))
        }
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        mPresenter = presenter
    }

}

