package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.HomewDiscountAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 * Author：pengllrn
 * Time: 2019/3/5 23:30
 */
class HomeFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()//不能在视图没有渲染完成之前，调用此方法，即onCreateView,onCreate

        initNews()

        initRecycler()

        initCoverflow()

    }

    private fun initBanner(){
//        mHomeBanner = view.find()
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE,
                HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)//动画效果
        mHomeBanner.setDelayTime(2000)//切换时间
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置（当banner模式中有指示器时），就是那几个点
        mHomeBanner.start()//启动
    }

    //初始化消息组件
    private fun initNews(){
        mNewFlipperView.setData(arrayOf("新用户立减100元","3月8日无限红利大放送，买就送福利"))
    }

    //初始化折扣组件
    private fun initRecycler(){
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        val discountAdapter = HomewDiscountAdapter(activity!!)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_TOPIC_TWO,
                HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    private fun initCoverflow(){
        //话题，画廊
        mTopicPager.adapter = TopicAdapter(context!!, listOf(HOME_TOPIC_ONE,
                HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f)
                .pagerMargin(-30.0f).spaceSize(0.0f).build()
    }


}