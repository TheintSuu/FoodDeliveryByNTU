package com.ci6222.fooddelivery.data.models

import android.graphics.Bitmap
import com.ci6222.fooddelivery.data.vos.UserVO
import com.ci6222.fooddelivery.network.auth.AuthManager


interface AuthenticationModel {
    var mAuthManager: AuthManager


    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
            userName: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserName(): String

    fun getUserProfile(  ) : UserVO

    fun uploadPhotoUrl(  photoUrl : Bitmap,
                         onSuccess: (String) -> Unit,
                         onFailure: (String) -> Unit)

    fun updateProfile(
            photoUrl : String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )
}