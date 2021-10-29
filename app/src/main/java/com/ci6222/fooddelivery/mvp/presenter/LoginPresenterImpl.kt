package com.ci6222.fooddelivery.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.data.models.AuthenticationModel
import com.ci6222.fooddelivery.data.models.AuthenticationModelImpl
import com.ci6222.fooddelivery.data.models.FoodModel
import com.ci6222.fooddelivery.data.models.FoodModelImpl
import com.ci6222.fooddelivery.mvp.view.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private  val mMainModel : FoodModel = FoodModelImpl

    override fun onUIReady(owner: LifecycleOwner) {
        mMainModel.setUpRemoteConfig()
        mMainModel.fetchRemoteConfigs()
    }

    override fun onTapLogin(email: String, password: String) {

        mAuthenticationModel.login(email, password, onSuccess = {
            mView?.navigateToMainScreen()
        }, onFailure = {

        })
    }

    override fun onTapRegister() {
        mView?.navigateToRegisterScreen()
    }

}