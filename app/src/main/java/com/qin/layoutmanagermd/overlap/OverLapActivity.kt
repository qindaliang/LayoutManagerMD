package com.qin.layoutmanagermd.overlap

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.qin.layoutmanagermd.R
import kotlinx.android.synthetic.main.activity_overlap.*

/**
 * Create by qindl
 * on 2018/11/27
 */
class OverLapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overlap)

        recyclerview.layoutManager = EchelonLayoutManager(this)
        recyclerview.adapter = MyAdapter()

    }

    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        private val icons = intArrayOf(R.drawable.header_icon_1, R.drawable.header_icon_2, R.drawable.header_icon_3, R.drawable.header_icon_4)
        private val bgs = intArrayOf(R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3, R.drawable.bg_4)
        private val nickNames = arrayOf("左耳近心", "凉雨初夏", "稚久九栀", "半窗疏影")
        private val descs = arrayOf("回不去的地方叫故乡 没有根的迁徙叫流浪...", "人生就像迷宫，我们用上半生找寻入口，用下半生找寻出口", "原来地久天长，只是误会一场", "不是故事的结局不够好，而是我们对故事的要求过多", "只想优雅转身，不料华丽撞墙")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_echelon, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.icon.setImageResource(icons[position % 4])
            holder.nickName.text = nickNames[position % 4]
            holder.desc.text = descs[position % 5]
            holder.bg.setImageResource(bgs[position % 4])
        }

        override fun getItemCount(): Int {
            return 60
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            internal var icon: ImageView
            internal var bg: ImageView
            internal var nickName: TextView
            internal var desc: TextView

            init {
                icon = itemView.findViewById(R.id.img_icon)
                bg = itemView.findViewById(R.id.img_bg)
                nickName = itemView.findViewById(R.id.tv_nickname)
                desc = itemView.findViewById(R.id.tv_desc)

            }
        }
    }
}