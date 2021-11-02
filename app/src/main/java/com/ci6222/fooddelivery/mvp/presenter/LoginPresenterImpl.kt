package com.ci6222.fooddelivery.mvp.presenter

import android.content.ContentValues.TAG
import android.provider.Settings.Secure.getString
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.datas.models.*
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.mvp.view.LoginView
import com.ci6222.fooddelivery.utilities.SessionManager


class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private  val mMainModel : FoodModel = FoodModelImpl

    private val mUser : UserModel = UserModelImpl

    override fun onUIReady(owner: LifecycleOwner) {
        mMainModel.setUpRemoteConfigWithDefaultValues()
        mMainModel.fetchRemoteConfigs()
    }

    override fun onTapLogin(email: String, password: String) {

        mAuthenticationModel.login(email, password, onSuccess = {

            mAuthenticationModel.userDataByEmail(email, onSuccess = {
                getUserDataByEmail(email, it)
            }, onFailure = {

            }


            )
        }, onFailure = {

        })
    }

    private fun getUserDataByEmail(email : String, user : UserVO){
        mUser.getUserByEmail(email, onSuccess = {
            SessionManager.userID = it
            mView?.navigateToMainScreen(user)
        }, onFaiure = {

        })
    }

    override fun onTapRegister() {
        mView?.navigateToRegisterScreen()
    }



}