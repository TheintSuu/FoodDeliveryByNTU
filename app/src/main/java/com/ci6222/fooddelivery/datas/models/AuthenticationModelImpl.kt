package com.ci6222.fooddelivery.datas.models

import android.graphics.Bitmap
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.network.auth.AuthManager
import com.ci6222.fooddelivery.network.auth.FirebaseAuthManager


object AuthenticationModelImpl : AuthenticationModel {

    override var mAuthManager: AuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
        username: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(username, email, password,phone, onSuccess, onFailure)
    }

    override fun userData(onSuccess: (userVO: UserVO) -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.userData( onSuccess, onFailure)
    }

    override fun userDataByEmail(
        email: String,
        onSuccess: (userVO: UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.userDataByEmail( email, onSuccess, onFailure)
    }

    override fun updateProfile(photoUrl: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.updateProfile(photoUrl, onSuccess, onFailure)
    }

}