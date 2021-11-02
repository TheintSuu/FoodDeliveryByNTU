package com.ci6222.fooddelivery.datas.models

import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.network.CloudFirestoreFirebaseApiImpl
import com.ci6222.fooddelivery.network.FirebaseApi
import com.ci6222.fooddelivery.network.remoteconfig.FirebaseRemoteConfigManager

object UserModelImpl : UserModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager =  FirebaseRemoteConfigManager
    override fun getUserByEmail(
        email: String,
        onSuccess: (id: String) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getUserByEmail(email, onSuccess, onFaiure)
    }

    override fun addUser(
        user: UserVO,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addUser(user, onSuccess, onFailure)
    }




    override fun getActivities(userId : String, onSuccess: (List<ActivityVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getOrderActivities(userId, onSuccess, onFaiure)
    }

    override fun addOrderActivity(
        userId: String,
        order : ActivityVO,
        onSuccess: (String) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.addOrderByUser(userId, order, onSuccess, onFaiure)
    }
}