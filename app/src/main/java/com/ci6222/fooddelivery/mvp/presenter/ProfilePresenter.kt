package com.ci6222.fooddelivery.mvp.presenter

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.ci6222.fooddelivery.mvp.view.ProfileView

interface ProfilePresenter : BasePresenter<ProfileView> {
    fun onUiReady(context : Context, lifecycleOwner: LifecycleOwner)
    fun updateUserProfile(bitmap: Bitmap)
    fun onTapCancelUserData()
    fun onTapLogOut()
    fun onTapEditProfileImage()
}