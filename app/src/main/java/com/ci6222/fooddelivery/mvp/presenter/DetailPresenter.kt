package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.delegates.DetailViewItemActionDelegate
import com.ci6222.fooddelivery.mvp.view.DetailView
import com.example.fooddeliveryapp.delegates.PopularChoiceDeatilViewItemActionDelegate

interface DetailPresenter  : BasePresenter<DetailView>,
    DetailViewItemActionDelegate,
    PopularChoiceDeatilViewItemActionDelegate {


    fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner)
    fun onRestaurantReceived(context: Context, lifecycleOwner: LifecycleOwner, documentId : String)

}