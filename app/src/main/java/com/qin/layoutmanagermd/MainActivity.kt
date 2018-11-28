package com.qin.layoutmanagermd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.qin.layoutmanagermd.viewpager.ViewPagerAdapter
import com.qin.layoutmanagermd.viewpager.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private lateinit var mManager: ViewPagerLayoutManager
    private lateinit var mAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    private fun initListener() {

        // 滑动监听
        mManager.setSelectedListener(object : ViewPagerLayoutManager.SelectedListener {
            override fun onSelected(postion: Int, hasNext: Boolean) {
                Log.i("TAG", "onSelected-----hasNext:  $hasNext-----postion: $postion ")
            }

            override fun onAttach() {
                Log.i("TAG", "onAttach")
            }

            override fun onDetach(postion: Int, isBottom: Boolean) {
                Log.i("TAG", "onDetach-----isBottom:  $isBottom-----postion: $postion ")
            }
        })

        // 事件点击监听
        mAdapter.setOnClickListener { holder, postion ->
           toast("${holder::class.simpleName}----$postion")
        }
    }

    private fun initView() {
        mManager = ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = ViewPagerAdapter(this)
        recyclerview.apply {
            layoutManager = mManager
            adapter = mAdapter
        }
    }

}
