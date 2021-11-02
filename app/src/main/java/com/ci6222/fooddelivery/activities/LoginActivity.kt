package com.ci6222.fooddelivery.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.mvp.presenter.LoginPresenter
import com.ci6222.fooddelivery.mvp.presenter.LoginPresenterImpl
import com.ci6222.fooddelivery.mvp.view.LoginView
import com.ci6222.fooddelivery.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    private lateinit var mPresenter: LoginPresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpPresenter()


        setUpActionListeners()

        mPresenter.onUIReady(this)
    }

    private fun setUpActionListeners() {

        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEmail.text.toString(), etPassword.text.toString())
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }


    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
        this.finish()
    }

    override fun navigateToMainScreen(patientVO : UserVO) {
        SessionManager.login_status = true
        savePatientInfo(patientVO)
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    private fun savePatientInfo(patientVO: UserVO){
        patientVO.id?.let {
            SessionManager.patient_name = patientVO.name
            //SessionManager.patient_device_token = patientVO.device_token
            SessionManager.patient_email = patientVO.email
            SessionManager.patient_photo = patientVO.image.toString()



    }

}
}