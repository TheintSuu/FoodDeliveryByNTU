package com.ci6222.fooddelivery.datas.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class CategoryVO(
        var category_id: String?= "",
        var category_name: String? = "",
        var category_image: String? = ""
)