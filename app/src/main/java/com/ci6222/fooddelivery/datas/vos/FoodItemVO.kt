package com.ci6222.fooddelivery.datas.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class FoodItemVO(
        var food_name: String?= "",
        var food_description: String? = "",
        var food_price: Double = 0.0,
        var food_rating: String? = "",
        var food_image: String? = "",
        var popular: Boolean = false,
        var itemCount: Long = 1,
        var totalAmount: Double = 0.0
)