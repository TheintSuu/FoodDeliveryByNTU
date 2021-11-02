package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.delegates.CategoryViewItemActionDelegate
import com.ci6222.fooddelivery.delegates.PopularViewItemActionDelegate
import com.ci6222.fooddelivery.delegates.RestaurantViewItemActionDelegate
import com.ci6222.fooddelivery.mvp.view.MainView


interface MainPresenter  : BasePresenter<MainView> , CategoryViewItemActionDelegate,
    RestaurantViewItemActionDelegate,
    PopularViewItemActionDelegate {
    fun  onUiReady(context : Context, lifecycleOwner: LifecycleOwner)
    fun onNavigateDetailScreen(documentId: String)
}