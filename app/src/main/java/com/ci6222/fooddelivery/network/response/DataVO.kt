package com.ci6222.fooddelivery.network.response

import com.google.gson.annotations.SerializedName

data class DataVO(
    @SerializedName("name")
    var title: String? = "",
    var body: String? = "",
    var id : String? = ""
)