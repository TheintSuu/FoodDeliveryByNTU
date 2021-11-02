package com.ci6222.fooddelivery.viewholders

import android.app.Activity
import android.view.View
import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.delegates.ActivityViewItemDelegate
import com.ci6222.fooddelivery.utilities.showImageWithoutCrop
import kotlinx.android.synthetic.main.view_holder_activity.view.*

class ActivityViewHolder (itemView : View, delegate : ActivityViewItemDelegate)
    : BaseViewHolder<ActivityVO>(itemView){
    val mDelegate = delegate
    init{
        itemView.setOnClickListener {
            mData?.let {
                it.let { it1 -> delegate.onTapDetail(it1.documentId.toString()) }
            }
        }



    }



    override fun bindData(data: ActivityVO) {
        mData = data


        data?.image_Url?.let{
               showImageWithoutCrop(itemView.ivPodcastImage,it)
           }

        //itemView.tvPodCastTimeLeft.text = data.audio_length_sec.toString()
        itemView.tvPodCastTime.text = data.date_time
        itemView.tvPodCastTitle.text = data.rest_name
        itemView.tvPodCasCategory.text = "$" + data.price


    }


}