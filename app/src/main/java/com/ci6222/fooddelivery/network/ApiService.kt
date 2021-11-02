package com.ci6222.fooddelivery.network

import com.ci6222.fooddelivery.network.response.NotiResponse
import com.ci6222.fooddelivery.utilities.NotificationVO
import com.ci6222.fooddelivery.utilities.SERVER_KEY
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-Type:application/json",
        "Authorization:key=$SERVER_KEY",
        "prioirity:high",
        "content_variable:true"
    )
    @POST("fcm/send")
    fun sendFcm(@Body notificationVO: NotificationVO) : Observable<NotiResponse>


}