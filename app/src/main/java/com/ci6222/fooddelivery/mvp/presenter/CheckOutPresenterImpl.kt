package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.datas.models.FoodModel
import com.ci6222.fooddelivery.datas.models.FoodModelImpl
import com.ci6222.fooddelivery.datas.models.UserModel
import com.ci6222.fooddelivery.datas.models.UserModelImpl
import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.mvp.view.CheckOutView
import com.ci6222.fooddelivery.utilities.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckOutPresenterImpl  : CheckOutPresenter, AbstractBasePresenter<CheckOutView>() {

    private val foodDeliveryModel : FoodModel = FoodModelImpl

    private val mUser : UserModel = UserModelImpl

    private var totalPrice :  String= ""

    override fun onTapCheckout(context: Context, orderList: List<FoodItemVO>, restInfo : RestaurantVO) {
        //sendEventsToFirebaseAnalytics(context, SCREEN_REGISTER)
        val orderActivity = ActivityVO()
        orderActivity.rest_name = restInfo.name
        orderActivity.image_Url = restInfo.image_Url
        orderActivity.date_time = getCurrentDateTime()
        orderActivity.documentId = restInfo.id
        orderActivity.price = totalPrice
        for(order in orderList) {
            foodDeliveryModel.removeFoodItem(order.food_name.toString())
        }
        mUser.addOrderActivity(SessionManager.userID.toString(), orderActivity, onSuccess = {

        }, onFaiure = {

        })

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_REGISTER)
        foodDeliveryModel.getOrderList(
            onSuccess = {
                mView?.showOrderList(it)
                calculatePrice()
            },
            onFaiure = {
                mView?.showError(it)
            })
    }

    override fun onTapTrack(context: Context, owner: LifecycleOwner) {
        var notiData = NotificationVO()
        var user  = UserVO()
         notiData  = prepareNotificationForOrder(context,  SessionManager.patient_device_token.toString(), user)
        foodDeliveryModel.sendDeliveryNotification(notiData, onSuccess = {
            Log.d("Noti send onsuccess", it.success.toString())
        }, onFailure = {
            Log.d("Noti Send", "failed")
        })

    }

    override fun onTapIncreaseAddToCartItem(foodItemVO: FoodItemVO) {
        var itemCount = foodItemVO.itemCount.toLong()
        var itemPrice = foodItemVO.food_price.toLong()
        if(itemCount>0)
        {
            itemCount++
        }
        foodItemVO.itemCount= itemCount
        var totalAmount= itemCount * itemPrice
        foodItemVO.totalAmount= totalAmount
        foodDeliveryModel.addOrUpdateFoodItem(foodItemVO)
        calculatePrice()
    }
    private  fun calculatePrice()
    {
        foodDeliveryModel.getTotalPrice(
            onSuccess = {
                totalPrice = it.toString()
                mView?.showCalculationCharge(it)
            },
            onFialure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapDecreaseAddToCartItem(foodItemVO: FoodItemVO) {
        var itemCount = foodItemVO.itemCount.toLong()
        var itemPrice = foodItemVO.food_price.toLong()
        if(itemCount>1)
        {
            itemCount--
        }
        foodItemVO.itemCount= itemCount
        var totalAmount= itemCount * itemPrice
        foodItemVO.totalAmount= totalAmount
        foodDeliveryModel.addOrUpdateFoodItem(foodItemVO)
        calculatePrice()
    }

    override fun onTapRemoveAddToCartItem(foodItemVO: FoodItemVO) {
        foodDeliveryModel.removeFoodItem(foodItemVO?.food_name.toString())
        calculatePrice()
    }


}
