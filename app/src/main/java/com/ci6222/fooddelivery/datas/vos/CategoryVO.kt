package com.ci6222.fooddelivery.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class CategoryVO(
        var id: String?= "",
        var name: String? = "",
        var image: String? = ""
)