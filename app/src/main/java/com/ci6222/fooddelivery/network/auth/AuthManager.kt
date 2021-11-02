package com.ci6222.fooddelivery.network.auth

import android.graphics.Bitmap
import com.ci6222.fooddelivery.datas.vos.UserVO


interface AuthManager {

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun register(username: String, email: String, password: String, phone: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun userData(onSuccess: (userVO : UserVO) -> Unit, onFailure: (String) -> Unit)
    fun userDataByEmail(email: String,onSuccess: (userVO : UserVO) -> Unit, onFailure: (String) -> Unit)
    fun updateProfile(   photoUrl : String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}