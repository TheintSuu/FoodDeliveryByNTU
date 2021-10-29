package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.mvp.view.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {

    fun onUIReady(context: Context, lifecycleOwner: LifecycleOwner)
    fun onTapRegister(context: Context, username: String, email: String, password: String, phone : String)
    fun onTapLogin()



}