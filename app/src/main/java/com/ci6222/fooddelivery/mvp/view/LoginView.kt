package com.ci6222.fooddelivery.mvp.view

import com.ci6222.fooddelivery.datas.vos.UserVO

interface LoginView : BaseView {

    fun navigateToRegisterScreen()

    fun navigateToMainScreen(userVO: UserVO)

}