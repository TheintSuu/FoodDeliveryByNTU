package com.ci6222.fooddelivery.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class UserVO(
    var name: String? = "",
    var email: String? = "",
    var image : String?= ""
)