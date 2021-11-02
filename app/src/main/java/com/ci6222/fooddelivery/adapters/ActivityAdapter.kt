package com.ci6222.fooddelivery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.delegates.ActivityViewItemDelegate
import com.ci6222.fooddelivery.delegates.CategoryViewItemActionDelegate
import com.ci6222.fooddelivery.mvp.view.ActivityView
import com.ci6222.fooddelivery.viewholders.ActivityViewHolder
import com.ci6222.fooddelivery.viewholders.CategoryViewHolder

class ActivityAdapter (private val mDelegate: ActivityViewItemDelegate) :
    BaseAdapter<ActivityViewHolder, ActivityVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_activity, parent, false)
        return ActivityViewHolder(view, mDelegate)
    }
}
