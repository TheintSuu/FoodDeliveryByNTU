package com.ci6222.fooddelivery.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.mvp.presenter.RegisterPresenter
import com.ci6222.fooddelivery.mvp.presenter.RegisterPresenterImpl
import com.ci6222.fooddelivery.mvp.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity :  BaseActivity() , RegisterView {

    private lateinit var mPresenter: RegisterPresenter


    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListeners()
    }


    private fun setUpActionListeners() {
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(this,
                etUserName.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                etPhone.text.toString()
            )
        }

        btnLogin.setOnClickListener {
            mPresenter.onTapLogin()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
        mPresenter.onUIReady(this,this)
    }



    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
        this.finish()
    }
}