package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.datas.models.FoodModel
import com.ci6222.fooddelivery.datas.models.FoodModelImpl
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.mvp.view.DetailView
import com.ci6222.fooddelivery.utilities.DISPLAY_RESTAURANT
import com.ci6222.fooddelivery.utilities.SCREEN_DETAIL

class DetailPresenterImpl : DetailPresenter, AbstractBasePresenter<DetailView>() {

    private val foodDeliveryModel : FoodModel = FoodModelImpl

    var foodList = arrayListOf<FoodItemVO>()




    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_DETAIL)
//        foodDeliveryModel.getCartItemCount(
//            onSuccess = {
//                mView?.showViewCartCount(it, false)
//            },
//            onFialure = {
//                mView?.showError(it)
//            })
    }

    override fun onTapAddToCartAction(data: FoodItemVO) {

        var totalAmount= data.itemCount * data.food_price

        data.totalAmount= totalAmount
        foodDeliveryModel.addOrUpdateFoodItem(data)

        foodDeliveryModel.getCartItemCount(
            onSuccess = {

                mView?.showViewCartCount(it, true)
            },
            onFialure = {
                mView?.showError(it)
            })
    }

    override fun onRestaurantReceived(context: Context, owner: LifecycleOwner, documentId: String) {
       // sendEventsToFirebaseAnalytics(context, DISPLAY_RESTAURANT)
        foodDeliveryModel.getFoodItems(
            documentId,
            onSuccess = {
                    dataList, restaurant ->
                mView?.showPopularChoicesFoodItem(
                    dataList.filter{
                            data -> data.popular
                    }
                )
                mView?.showRestaurantData(restaurant)
                mView?.showFoodItemList(dataList)
            },
            onFaiure = {
                mView?.showError(it)
            })
    }

    override fun onTapPopularChoiceDelegateAction() {}

    override fun onBackMain() {
        //foodDeliveryModel.removeCart()
    }
}
