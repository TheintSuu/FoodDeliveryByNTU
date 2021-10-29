package com.ci6222.fooddelivery.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseMessageService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("token", token)
        super.onNewToken(token)
    }
}