package com.ci6222.fooddelivery.mvp.view

import com.ci6222.fooddelivery.datas.vos.FoodItemVO

interface CheckOutView : BaseView {
    fun showOrderList(orderList: List<FoodItemVO>)
    fun showCalculationCharge(totalAmount: Double)

}