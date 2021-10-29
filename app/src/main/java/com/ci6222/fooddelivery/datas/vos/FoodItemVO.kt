package com.ci6222.fooddelivery.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class FoodItemVO(
        var name: String?= "",
        var description: String? = "",
        var price: Long = 0,
        var rating: String? = "",
        var imgurl: String? = "",
        var popular: Boolean = false,
        var count: Long =1,
        var totalAmount: Long =0
)