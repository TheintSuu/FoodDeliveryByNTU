package com.ci6222.fooddelivery.datas.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class UserVO(
    var id : String ? = "",
    var name: String? = "",
    var email: String? = "",
    var image : String?= "",
    var device_token : String ?= "",
    var password : String?= "",
    var phone : String ?= ""
)