package com.ci6222.fooddelivery

import android.app.Application
import com.ci6222.fooddelivery.utilities.SessionManager


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SessionManager.init(applicationContext)
    }
}