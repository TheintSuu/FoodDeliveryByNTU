package com.ci6222.fooddelivery.utilities

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.network.response.DataVO
import java.text.SimpleDateFormat
import java.util.*


fun showImage(context: Context, imageView: ImageView, imageUrl: String?, filePath: Uri?)
{
    Glide.with(context)
        .asBitmap()
        .load(filePath ?: imageUrl)
        .placeholder(R.drawable.profile_thumb)
        .apply(RequestOptions().circleCrop())
        .into(imageView)
}

fun showImageWithoutCrop(imageView: ImageView, imageUrl: String)
{
    Glide.with(imageView.context)
        .load( imageUrl)
        .placeholder(R.drawable.thumbnail)
        .into(imageView)
}

fun prepareNotificationForOrder(context: Context, to:String?, data : UserVO): NotificationVO {
    val notificationVO = NotificationVO()
    val dataVO = DataVO()
    notificationVO.to = to.toString()
    dataVO.title = "Order is picked by driver"
    dataVO.body = "Your driver is on the way"
    dataVO.id = data.id
    notificationVO.data = dataVO
    return notificationVO
}

fun getCurrentDateTime() : String{
    return SimpleDateFormat("dd MMM, yyyy  HH:mm a").format(Date())
}

fun getCurrentHourMinAMPM() : String{
    return SimpleDateFormat("hh:mm a").format(Date())
}