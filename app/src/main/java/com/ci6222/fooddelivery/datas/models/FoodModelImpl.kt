package com.ci6222.fooddelivery.data.models

import com.ci6222.fooddelivery.data.vos.CategoryVO
import com.ci6222.fooddelivery.data.vos.FoodItemVO
import com.ci6222.fooddelivery.data.vos.ShopVO
import com.ci6222.fooddelivery.network.CloudFirestoreFirebaseApiImpl
import com.ci6222.fooddelivery.network.FirebaseApi
import com.ci6222.fooddelivery.network.remoteconfig.FirebaseRemoteConfigManager


object FoodModelImpl : FoodModel {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager = FirebaseRemoteConfigManager


    override fun getCategories(onSuccess: (List<CategoryVO>) -> Unit, onFaiure: (String) -> Unit) {
       mFirebaseApi.getCategory(onSuccess, onFaiure)
    }

    override fun getRestaurants(onSuccess: (List<ShopVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getRestaurants(onSuccess, onFaiure)
    }

    override fun getAllFoodItemsByShop(
        shopId: String,
        onSuccess: (List<FoodItemVO>, ShopVO) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getDetailFoodList(shopId, onSuccess, onFaiure)
    }

    override fun getPopularFoodItems(onSuccess: (List<FoodItemVO>) -> Unit, onFaiure: (String) -> Unit) {
       mFirebaseApi.getPopularFoodItemList(onSuccess, onFaiure)
    }

    override fun getTotalFoodItemCount(onSuccess: (count: Long) -> Unit, onFialure: (String) -> Unit) {
        mFirebaseApi.getCartFoodItemCount(onSuccess,onFialure)
    }

    override fun getTotalPrice(onSuccess: (count: Long) -> Unit, onFialure: (String) -> Unit) {
        mFirebaseApi.getTotalPrice(onSuccess,onFialure)
    }

    override fun addFoodItem(item: FoodItemVO) {
     mFirebaseApi.addFoodItem(item)
    }

    override fun setUpRemoteConfig() {
       mFirebaseRemoteConfigManager.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfigs() {
     mFirebaseRemoteConfigManager.fetchRemoteConfigs()
    }

    override fun getHomeScreenViewType(): Int {
      val v =  mFirebaseRemoteConfigManager.getMainScreenViewType().toInt()
        return v
    }
}