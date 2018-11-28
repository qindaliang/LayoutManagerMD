package com.qin.layoutmanagermd.viewpager

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Create by qindl
 * on 2018/11/27
 */
class ViewPagerLayoutManager : LinearLayoutManager, View.OnAttachStateChangeListener {

    private val mPagerSnapHelper = PagerSnapHelper()
    private lateinit var mRecyclerView: RecyclerView
    private var mOffSet: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    init {

    }

    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        mPagerSnapHelper.attachToRecyclerView(view)
        this.mRecyclerView = view
        mRecyclerView.addOnAttachStateChangeListener(this)
    }

    /**
     * 依附到窗口时判断
     */
    override fun onViewDetachedFromWindow(v: View?) {
        if (mOffSet >= 0) {
            mListener?.onDetach(getPosition(v),true)
        } else {
            mListener?.onDetach(getPosition(v),false)
        }
    }

    override fun onViewAttachedToWindow(v: View?) {
        // 确定只有一个View显示
        if (childCount == 1) {
            mListener?.onAttach()
        }
    }

    /**
     *水平滑动需要重写的方法
     */
    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        mOffSet = dx
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    /**
     *垂直滑动需要重写的方法
     */
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        mOffSet = dy
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)

        when (state) {
            // 停止状态
            RecyclerView.SCROLL_STATE_IDLE -> {
                // 获取PagerSnapHelper对应的View
                val snapView = mPagerSnapHelper.findSnapView(this)
                // 获取对应的位置
                val position = getPosition(snapView)
                if (childCount == 1)
                    mListener?.onSelected(position, position == itemCount - 1)
            }
            // 拖拽
            RecyclerView.SCROLL_STATE_DRAGGING -> {

            }
        }

    }

    // 接口回调

    private var mListener: SelectedListener? = null

    interface SelectedListener {
        fun onSelected(postion: Int, hasNext: Boolean)
        fun onAttach()
        fun onDetach(postion: Int, isBottom: Boolean)
    }

    fun setSelectedListener(listener: SelectedListener) {
        this.mListener = listener
    }

}