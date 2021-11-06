package com.ci6222.fooddelivery.mvp.view

import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO

interface DetailView  : BaseView {
    fun showRestaurantData(restaurantVO: RestaurantVO)
    fun showPopularChoicesFoodItem(popularFoodList: List<FoodItemVO>)
    fun showFoodItemList(foodList: List<FoodItemVO>)
    fun showViewCartCount(cartCount : Long, startCart:  Boolean )
}