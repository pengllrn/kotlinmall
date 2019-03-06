package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.HOME_BANNER_FOUR
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

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

    private fun initNews(){

    }
}