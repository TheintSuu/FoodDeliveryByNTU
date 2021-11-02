package com.ci6222.fooddelivery.datas.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ActivityVO(
    var id: String? = "",
    var date_time : String? = "",
    var image_Url: String? = "",
    var documentId : String?= "",
    var rest_name: String? = "",
    var price: String? = ""
)
