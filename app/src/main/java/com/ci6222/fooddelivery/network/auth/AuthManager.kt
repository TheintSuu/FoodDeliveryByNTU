package com.ci6222.fooddelivery.network.auth

import android.graphics.Bitmap
import com.ci6222.fooddelivery.data.vos.UserVO


interface AuthManager {
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun register(email: String, password: String, userName: String, phone : String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getUserName() : String
    fun getAllUserData() : UserVO
    fun uploadPhototUrl (photoUrl : Bitmap,
                         onSuccess: (String) -> Unit,
                         onFailure: (String) -> Unit)
    fun updateProfile(photoUrl : String,
                      onSuccess: () -> Unit,
                      onFailure: (String) -> Unit)
}