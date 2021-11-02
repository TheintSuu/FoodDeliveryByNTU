package com.ci6222.fooddelivery.mvp.view

import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO

interface ActivityView  : BaseView {

    fun showCheckOutData(popularFoodList: List<ActivityVO>)
    fun navigateToDetail(documentID : String)
}