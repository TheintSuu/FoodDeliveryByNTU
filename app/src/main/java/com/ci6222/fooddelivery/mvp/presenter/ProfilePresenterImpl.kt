package com.ci6222.fooddelivery.mvp.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.datas.models.AuthenticationModel
import com.ci6222.fooddelivery.datas.models.AuthenticationModelImpl
import com.ci6222.fooddelivery.datas.models.FoodModel
import com.ci6222.fooddelivery.datas.models.FoodModelImpl
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.fragments.BaseFragment
import com.ci6222.fooddelivery.fragments.ProfileFragment
import com.ci6222.fooddelivery.mvp.view.ProfileView
import com.ci6222.fooddelivery.utilities.SCREEN_PROFILE
import com.ci6222.fooddelivery.utilities.showImage
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.IOException

class ProfilePresenterImpl  : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private  val foodDeliveryModel : FoodModel = FoodModelImpl

    override fun updateUserProfile(bitmap: Bitmap) {
        foodDeliveryModel.uploadPhotoToFirebaseStorage(bitmap,
            onSuccess = {
                mView?.onTapSaveUserData()
                mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})
            },
            onFailure = {
                mView?.showError("Profile Updat Failed")
            })

    }

    override fun onTapCancelUserData() {
        mView?.onTapCancelUserData()
    }

    override fun onTapLogOut() {
       mView?.displayLoginScreen()
    }

    override fun onTapEditProfileImage() {
        mView?.onTapEditProfileImage()
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_PROFILE)
        mAuthenticationModel?.userData(
            onSuccess = {
                mView?.displayUserData(it)
            },
            onFailure = {}
        )
    }
}