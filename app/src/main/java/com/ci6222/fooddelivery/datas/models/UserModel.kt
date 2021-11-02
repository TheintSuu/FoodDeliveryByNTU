package com.ci6222.fooddelivery.datas.models

import android.graphics.Bitmap
import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.network.FirebaseApi
import com.ci6222.fooddelivery.network.remoteconfig.FirebaseRemoteConfigManager

interface UserModel {

    var mFirebaseApi : FirebaseApi

    var mFirebaseRemoteConfigManager : FirebaseRemoteConfigManager


    fun getUserByEmail(email : String, onSuccess: (id: String) -> Unit, onFaiure: (String) -> Unit)

    fun addUser(user: UserVO, onSuccess: (id: String) -> Unit, onFailure: (String) -> Unit)

    fun getActivities(userId : String, onSuccess: (List<ActivityVO>) -> Unit, onFaiure: (String) -> Unit)
    fun addOrderActivity(userId : String, order : ActivityVO, onSuccess: (String) -> Unit, onFaiure: (String) -> Unit)
}