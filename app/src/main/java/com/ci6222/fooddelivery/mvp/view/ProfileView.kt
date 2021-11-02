package com.ci6222.fooddelivery.mvp.view

import com.ci6222.fooddelivery.datas.vos.UserVO

interface ProfileView : BaseView {
    fun onTapSaveUserData()
    fun onTapCancelUserData()
    fun onTapEditProfileImage()
    fun displayUserData(userVO: UserVO)
    fun displayLoginScreen()
}