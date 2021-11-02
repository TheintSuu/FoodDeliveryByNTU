package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.delegates.CheckoutViewItemActionDelegate
import com.ci6222.fooddelivery.mvp.view.CheckOutView

interface CheckOutPresenter : BasePresenter<CheckOutView>, CheckoutViewItemActionDelegate {

    fun onUiReady(context: Context, owner: LifecycleOwner)

    fun onTapTrack(context: Context, owner: LifecycleOwner)

    fun onTapCheckout(context: Context, orderList: List<FoodItemVO>, res: RestaurantVO)

}