package com.qin.layoutmanagermd.overlap

import android.support.v7.widget.RecyclerView

/**
 * Create by qindl
 * on 2018/11/27
 */
class OverLapLayoutManager :RecyclerView.LayoutManager(){

    /**
     * 必须重写的方法
     */
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    /**
     * 对其子view进行布局排列
     */
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)

    }


}