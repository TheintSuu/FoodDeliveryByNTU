package com.ci6222.fooddelivery.network

import android.graphics.Bitmap
import com.ci6222.fooddelivery.datas.vos.*

interface FirebaseApi  {
    fun addUser(user: UserVO, onSuccess: (documentId: String) -> Unit, onFialure: (String) -> Unit)
    fun  getUserByEmail(email: String, onSuccess: (documentId: String) -> Unit, onFialure: (String) -> Unit)
    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)
    fun getCategories(onSuccess: (categories: List<CategoryVO>) -> Unit, onFialure: (String) -> Unit)
    fun getRestaurants(onSuccess: (restaurants: List<RestaurantVO>) -> Unit, onFialure: (String) -> Unit)
    fun getOrderActivities(userId: String, onSuccess: (restaurants: List<ActivityVO>) -> Unit, onFialure: (String) -> Unit)
    fun getFoodItems( documentId: String, onSuccess: (foodList: List<FoodItemVO>, restaurantVO : RestaurantVO) -> Unit, onFialure: (String) -> Unit)
    fun getPopularChoiceList(onSuccess: (restaurants: List<FoodItemVO>) -> Unit, onFialure: (String) -> Unit)
    fun getOrderList(onSuccess: (restaurants: List<FoodItemVO>) -> Unit, onFialure: (String) -> Unit)
    fun addOrUpdateFoodItem(foodItemVO: FoodItemVO)
    fun addOrderByUser(userId: String, order : ActivityVO, onSuccess: (String) -> Unit, onFialure: (String) -> Unit)
    fun deleteFoodItem(id: String)
    fun getCartItemCount(onSuccess: (cartCount: Long) -> Unit, onFialure: (String) -> Unit)
    fun getTotalPrice(onSuccess: (cartCount: Long) -> Unit, onFialure: (String) -> Unit)
}