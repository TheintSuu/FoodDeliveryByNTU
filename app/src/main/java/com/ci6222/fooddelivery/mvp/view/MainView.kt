package com.ci6222.fooddelivery.mvp.view

import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO

interface MainView : BaseView {
    fun changeHomeScreenViewType( viewType : Int)
    fun navigateToDetailScreen(documentId: String)
    fun showErrorMessage(message: String)
    fun showCategories(categoryList: List<CategoryVO>)
    fun showRestaurants(restaurantList: List<RestaurantVO>)
    fun showPopularChoicesFoodItems(popularChoiceList: List<FoodItemVO>)
}