package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.datas.models.AuthenticationModel
import com.ci6222.fooddelivery.datas.models.AuthenticationModelImpl
import com.ci6222.fooddelivery.datas.models.UserModel
import com.ci6222.fooddelivery.datas.models.UserModelImpl
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.mvp.view.RegisterView
import com.ci6222.fooddelivery.utilities.SCREEN_REGISTER
import com.ci6222.fooddelivery.utilities.SessionManager
import com.ci6222.fooddelivery.utilities.TAP_REGISTER

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mUser : UserModel = UserModelImpl
    override fun onUIReady(context : Context, life : LifecycleOwner){
        sendEventsToFirebaseAnalytics(context, SCREEN_REGISTER)

    }

    override fun onTapRegister(context: Context, username: String, email: String, password: String, phone : String) {

        val user = UserVO()
        user.email = email
        user.name = username
        user.phone = phone
        user.device_token = SessionManager.patient_device_token
        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            mView?.showError("Please enter all fieldes")
        }else{
            sendEventsToFirebaseAnalytics(context, TAP_REGISTER)
            mAuthenticationModel.register(username, email, password,phone, onSuccess = {
                addUser(user)
                mView?.navigateToLoginScreen()

            }, onFailure = {
                mView?.showError(it)
            })
        }

    }

    private fun addUser(user: UserVO){
        mUser.addUser(user, onSuccess = {

        }, onFailure = {
            mView?.showError(it)
        })
    }


    override fun onTapLogin() {
        mView?.navigateToLoginScreen()
    }


}