package com.pengllrn.message.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.pengllrn.message.R
import org.jetbrains.anko.dimen
import org.jetbrains.anko.find
import org.jetbrains.anko.px2sp

/**
 * Author：pengllrn
 * Time: 2019/3/6 14:40
 * 自定义消息视图——公告组件封装
 * 首页--切换控件
 */
class NewsFlipperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mFlipperView:ViewFlipper  //ViewFlipper是一个切换控件，一般用于图片的切换
    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper,null)

        mFlipperView = rootView.find(R.id.mFlipperView)//
        mFlipperView.setInAnimation(context,R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context,R.anim.news_bottom_out)

        addView(rootView)
    }

    /*
    构建公告
     */
    private fun buildNewView(text:String):View{
        val textView = TextView(context)
        textView.text = text
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT)

        return textView
    }

    /*
    设置公告数据
     */
    fun setData(data:Array<String>){
        data.forEach {
            mFlipperView.addView(buildNewView(it))
        }
        mFlipperView.startFlipping()
    }
}