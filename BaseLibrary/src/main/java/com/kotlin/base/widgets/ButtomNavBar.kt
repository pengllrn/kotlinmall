package com.kotlin.base.widgets
import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.kotlin.base.R

/**
 * Author：pengllrn
 * Time: 2019/3/5 22:21
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val mCartBadge:TextBadgeItem //购物车图标  文字标签--标签带文字
    private val mMsgBadge:ShapeBadgeItem  //消息图标  形状标签

    init{
        //首页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press,
                resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press,
                resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press,
                resources.getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        mCartBadge = TextBadgeItem()
        mCartBadge.setText("10")
        cartItem.setBadgeItem(mCartBadge)//设置图标

        //消息
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press,
                resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        mMsgBadge = ShapeBadgeItem()
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgItem.setBadgeItem(mMsgBadge)

        //我的
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press,
                resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        setMode(BottomNavigationBar.MODE_FIXED)//模式
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)//背景风格
        setBarBackgroundColor(R.color.common_white)//背景颜色

        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)//默认选择
                .initialise()
    }

    fun checkCartBadge(count:Int){
        if(count == 0){
            mCartBadge.hide()
        }else{
            mCartBadge.show()
            mCartBadge.setText("$count")
        }
    }

    fun checkMsgBadge(isVisiable:Boolean){
        if(isVisiable){
            mMsgBadge.show()
        }else
            mMsgBadge.hide()
    }
}