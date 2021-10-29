package com.ci6222.fooddelivery.adapters

import androidx.recyclerview.widget.RecyclerView
import com.ci6222.fooddelivery.viewholders.BaseViewHolder


abstract class BaseAdapter<T: BaseViewHolder<W>,W> : RecyclerView.Adapter<T>() {

    var mData: MutableList<W> = mutableListOf()
    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setData(data : MutableList<W>){
        mData = data
        notifyDataSetChanged()
    }

    fun appendNewData(data : List<W>){
        mData.addAll(data)
        notifyDataSetChanged()
    }
}