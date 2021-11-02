package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.datas.models.UserModel
import com.ci6222.fooddelivery.datas.models.UserModelImpl
import com.ci6222.fooddelivery.datas.models.FoodModel
import com.ci6222.fooddelivery.datas.models.FoodModelImpl
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.mvp.view.ActivityView
import com.ci6222.fooddelivery.utilities.SCREEN_DETAIL
import com.ci6222.fooddelivery.utilities.SessionManager

class ActivityPresenterImpl : ActivityPresenter, AbstractBasePresenter<ActivityView>() {

    private val foodDeliveryModel : FoodModel = FoodModelImpl

    private val userModel : UserModel = UserModelImpl
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_DETAIL)
        userModel.getActivities(SessionManager.userID.toString(), onSuccess = {
            mView?.showCheckOutData(it)
        }, onFaiure = {

        })
    }

    override fun onTapTryAgain() {

    }

    override fun onTapDetail(documented: String) {
        mView?.navigateToDetail(documented)
    }




}
