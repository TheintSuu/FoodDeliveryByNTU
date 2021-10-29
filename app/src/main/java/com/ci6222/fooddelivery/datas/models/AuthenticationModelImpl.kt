package com.ci6222.fooddelivery.data.models

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import com.ci6222.fooddelivery.data.vos.UserVO
import com.ci6222.fooddelivery.network.auth.AuthManager
import com.ci6222.fooddelivery.network.auth.FirebaseAuthManager


object AuthenticationModelImpl : AuthenticationModel {

    override var mAuthManager : AuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
        phone : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(email, password, userName, phone, onSuccess, onFailure)
    }

    override fun getUserName(): String {

        return mAuthManager.getUserName()
    }

    override fun getUserProfile() : UserVO {
        return mAuthManager.getAllUserData()
  }

    override fun uploadPhotoUrl(photoUrl: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.uploadPhototUrl(photoUrl, onSuccess, onFailure)
    }



    override fun updateProfile(
        url: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.updateProfile(url, onSuccess, onFailure)
    }


}