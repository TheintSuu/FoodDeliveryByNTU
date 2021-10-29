package com.ci6222.fooddelivery.mvp.presenter

import com.ci6222.fooddelivery.mvp.view.BaseView


interface BasePresenter<T : BaseView> {

    fun initPresenter(view: T)



}