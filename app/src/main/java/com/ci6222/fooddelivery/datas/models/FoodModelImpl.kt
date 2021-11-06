package com.ci6222.fooddelivery.datas.models

import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.network.CloudFirestoreFirebaseApiImpl
import com.ci6222.fooddelivery.network.FirebaseApi
import com.ci6222.fooddelivery.network.remoteconfig.FirebaseRemoteConfigManager
import com.ci6222.fooddelivery.network.response.NotiResponse
import com.ci6222.fooddelivery.utilities.NotificationVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object FoodModelImpl : FoodModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override  var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager = FirebaseRemoteConfigManager

    override fun setUpRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfigManager.setUpRemoteConfigWithDeaultValues()
    }

    override fun fetchRemoteConfigs() {
        mFirebaseRemoteConfigManager.fetchRemoteConfigs()
    }

    override fun getHomeScreenTypeStatusFromRemoteConfig(): Int {
        return  mFirebaseRemoteConfigManager.getHomeScreenViewTypeStatus()
    }

    override fun uploadPhotoToFirebaseStorage(image: Bitmap , onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.uploadPhotoToFirebaseStorage(image ,onSuccess,onFailure)
    }


    override fun getCategories(onSuccess: (List<CategoryVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getCategories(onSuccess, onFaiure)
    }

    override fun getRestaurants(onSuccess: (List<RestaurantVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getRestaurants(onSuccess, onFaiure)
    }



    override fun getFoodItems(
        documentId: String,
        onSuccess: (List<FoodItemVO>,RestaurantVO) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getFoodItems(documentId,onSuccess, onFaiure)
    }

    override fun getPopularChoiceList(
        onSuccess: (List<FoodItemVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getPopularChoiceList(onSuccess, onFaiure)
    }

    override fun getOrderList(onSuccess: (List<FoodItemVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getOrderList(onSuccess, onFaiure)
    }


    override fun addOrUpdateFoodItem(foodItemVO: FoodItemVO) {
        mFirebaseApi.createOrder()
        mFirebaseApi.addOrUpdateFoodItem(foodItemVO)
    }

    override fun getCartItemCount(onSuccess: (cartCount: Long) -> Unit, onFialure: (String) -> Unit) {
        mFirebaseApi.getCartItemCount(onSuccess,onFialure)
    }

    override fun getTotalPrice(onSuccess: (cartCount: Double) -> Unit, onFialure: (String) -> Unit) {
        mFirebaseApi.getTotalPrice(onSuccess,onFialure)
    }

    override fun removeFoodItem(id: String) {
        mFirebaseApi.deleteFoodItem(id)
    }

    override fun removeCart() {
        mFirebaseApi.deleteOrder()
    }


    @SuppressLint("CheckResult")
    override fun sendDeliveryNotification(notificationVO: NotificationVO, onSuccess: (notiResponse: NotiResponse) -> Unit, onFailure: (String) -> Unit) {
        mApi.sendFcm(notificationVO)
            .map { it }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { data ->
                    onSuccess(it)
                }
            }, {
                onFailure(it.localizedMessage ?: "ERROR MESSAGE")
            })
    }


}