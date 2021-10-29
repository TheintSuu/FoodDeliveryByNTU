package com.ci6222.fooddelivery.data.models

import com.ci6222.fooddelivery.data.vos.CategoryVO
import com.ci6222.fooddelivery.data.vos.FoodItemVO
import com.ci6222.fooddelivery.data.vos.ShopVO
import com.ci6222.fooddelivery.network.FirebaseApi
import com.ci6222.fooddelivery.network.remoteconfig.FirebaseRemoteConfigManager


interface FoodModel {
    var mFirebaseApi : FirebaseApi

    var mFirebaseRemoteConfigManager : FirebaseRemoteConfigManager


    fun getCategories(onSuccess: (List<CategoryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getRestaurants(onSuccess: (List<ShopVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getAllFoodItemsByShop(shopId: String, onSuccess: (List<FoodItemVO>, ShopVO) -> Unit, onFaiure: (String) -> Unit)

    fun getPopularFoodItems( onSuccess: (List<FoodItemVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getTotalFoodItemCount(onSuccess: (count: Long) -> Unit, onFialure: (String) -> Unit)

    fun getTotalPrice(onSuccess: (count: Long) -> Unit, onFialure: (String) -> Unit)

    fun addFoodItem(item : FoodItemVO)

    fun setUpRemoteConfig()

    fun fetchRemoteConfigs()

    fun getHomeScreenViewType() : Int
}