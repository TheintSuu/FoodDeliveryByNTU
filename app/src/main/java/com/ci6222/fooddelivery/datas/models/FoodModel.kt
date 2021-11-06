package com.ci6222.fooddelivery.datas.models

import android.graphics.Bitmap
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.network.FirebaseApi
import com.ci6222.fooddelivery.network.remoteconfig.FirebaseRemoteConfigManager
import com.ci6222.fooddelivery.network.response.NotiResponse
import com.ci6222.fooddelivery.utilities.NotificationVO


interface FoodModel {
    var mFirebaseApi : FirebaseApi

    var mFirebaseRemoteConfigManager : FirebaseRemoteConfigManager

    fun setUpRemoteConfigWithDefaultValues()

    fun fetchRemoteConfigs()

    fun getHomeScreenTypeStatusFromRemoteConfig() : Int

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun getCategories(onSuccess: (List<CategoryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getRestaurants(onSuccess: (List<RestaurantVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getFoodItems(documentId: String, onSuccess: (List<FoodItemVO>, RestaurantVO) -> Unit, onFaiure: (String) -> Unit)

    fun getPopularChoiceList(onSuccess: (List<FoodItemVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getOrderList(onSuccess: (List<FoodItemVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addOrUpdateFoodItem(foodItemVO: FoodItemVO)

    fun removeFoodItem(name: String)

    fun removeCart()

    fun sendDeliveryNotification(notificationVO: NotificationVO, onSuccess: (notiResponse: NotiResponse) -> Unit, onFailure: (String) -> Unit)

    fun getCartItemCount(onSuccess: (cartCount: Long) -> Unit, onFialure: (String) -> Unit)

    fun getTotalPrice(onSuccess: (cartCount: Double) -> Unit, onFialure: (String) -> Unit)

}