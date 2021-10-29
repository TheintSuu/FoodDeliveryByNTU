package com.ci6222.fooddelivery.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.mvp.view.LoginView

interface LoginPresenter : BasePresenter<LoginView> {

    fun onUIReady(lifecycleOwner: LifecycleOwner)
    fun onTapLogin(email:String, password:String)
    fun onTapRegister()

}