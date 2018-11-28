package com.qin.layoutmanagermd.viewpager

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.qin.layoutmanagermd.R

/**
 * Create by qindl
 * on 2018/11/27
 */
class ViewPagerAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val iamges by lazy {
        mutableListOf(R.drawable.dog_1, R.drawable.dog_2, R.drawable.dog_3, R.drawable.dog_4, R.drawable.dog_5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, parent, false))
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as MyViewHolder
        holder1.image.setImageResource(iamges[position%iamges.size])
        holder1.itemView.setOnClickListener { mListener.invoke(holder,position%iamges.size) }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.iv_viewpager)
    }

    private lateinit var mListener:(MyViewHolder,Int)->Unit

    fun setOnClickListener(listener:(MyViewHolder,Int)->Unit){
        this.mListener = listener
    }
}