package com.kotlin.mall.ui.activity

import android.os.Bundle
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author：pengllrn
 * Time: 2019/3/5 23:12
 */
class MainActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

//        Observable.timer(2,TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({//2秒之后执行的操作
//                    mBottomNavBar.checkMsgBadge(true)})

    }
}