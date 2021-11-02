package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.mvp.view.MainView
import com.ci6222.fooddelivery.datas.models.FoodModel
import com.ci6222.fooddelivery.datas.models.FoodModelImpl
import com.ci6222.fooddelivery.utilities.SCREEN_HOME



class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val foodDeliveryModel : FoodModel = FoodModelImpl

    override fun onNavigateDetailScreen(documentId : String) {
        mView?.navigateToDetailScreen(documentId)
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_HOME)
        mView?.changeHomeScreenViewType(foodDeliveryModel.getHomeScreenTypeStatusFromRemoteConfig())

        foodDeliveryModel.getCategories(
            onSuccess = {
                mView?.showCategories(it)
            },
            onFaiure = {
                mView?.showErrorMessage(it)
            })

        foodDeliveryModel.getRestaurants(
            onSuccess = {
                mView?.showRestaurants(it)
            },
            onFaiure = {
                mView?.showErrorMessage(it)
            })

        foodDeliveryModel.getPopularChoiceList(
            onSuccess = {
                mView?.showPopularChoicesFoodItems(it)
            },
            onFaiure = {
                mView?.showErrorMessage(it)
            })

    }

    override fun onTap() {

    }

    override fun onTapRestaurentListItem(documentId: String) {
        mView?.navigateToDetailScreen(documentId)
    }

    override fun onTapPopularChoiceListItem() {

    }

}