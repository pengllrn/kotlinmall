package com.kotlin.mall.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.GlideUtils
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * Author：pengllrn
 * Time: 2019/3/6 16:36
 * T:图片地址
 *
 */
class HomewDiscountAdapter(context:Context):BaseRecyclerViewAdapter<String,
        HomewDiscountAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(mContext)
               .inflate(R.layout.layout_home_discount_item,parent,false)
        return ViewHolder(view)
    }

    //绑定数据
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        //holder.itemView.mGoodsIconIv
        //Kotlin的拓展可以在Activity、Fragment,Adapter中使用
        //holder-->当前视图-->组件
        GlideUtils.loadUrlImage(mContext,dataList[position],holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "￥123.00"
        holder.itemView.mDiscountBeforeTv.text = "$1000.00"
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }
}