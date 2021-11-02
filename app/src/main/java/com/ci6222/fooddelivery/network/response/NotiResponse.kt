package com.ci6222.fooddelivery.network.response

import com.google.gson.annotations.SerializedName

data class NotiResponse(
    @SerializedName("success")
    var success: String? = "",
    @SerializedName("failure")
    var failure: String? =""
) {
}
