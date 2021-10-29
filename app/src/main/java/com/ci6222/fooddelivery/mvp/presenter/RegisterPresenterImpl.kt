package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.data.models.AuthenticationModel
import com.ci6222.fooddelivery.data.models.AuthenticationModelImpl
import com.ci6222.fooddelivery.mvp.view.RegisterView
import com.ci6222.fooddelivery.utilities.SCREEN_REGISTER
import com.ci6222.fooddelivery.utilities.TAP_REGISTER

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onUIReady(context : Context, life : LifecycleOwner){
        sendEventsToFirebaseAnalytics(context, SCREEN_REGISTER)

    }

    override fun onTapRegister(context: Context, username: String, email: String, password: String, phone : String) {


        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            mView?.showError("Please enter all fieldes")
        }else{
            sendEventsToFirebaseAnalytics(context, TAP_REGISTER)
            mAuthenticationModel.register(username, email, password,phone, onSuccess = {
                mView?.navigateToLoginScreen()
            }, onFailure = {
                mView?.showError(it)
            })
        }

    }


    override fun onTapLogin() {
        mView?.navigateToLoginScreen()
    }


}