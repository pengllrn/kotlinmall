package com.kotlin.mall.ui.activity

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.mall.R
import com.kotlin.mall.ui.fragment.HomeFragment
import com.kotlin.mall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author：pengllrn
 * Time: 2019/3/5 23:12
 */
class MainActivity:BaseActivity() {
    private val mList = ArrayList<BaseFragment>(5)
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

//        Observable.timer(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({//2秒之后执行的操作
//                    mBottomNavBar.checkMsgBadge(true)})
        initFragment()
        initBottomNav()
        changgeFragment(0)
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContainer,mHomeFragment)
        manager.add(R.id.mContainer,mCategoryFragment)
        manager.add(R.id.mContainer,mCartFragment)
        manager.add(R.id.mContainer,mMsgFragment)
        manager.add(R.id.mContainer,mMeFragment)
        manager.commit()

        mList.add(mHomeFragment)
        mList.add(mCategoryFragment)
        mList.add(mCartFragment)
        mList.add(mMsgFragment)
        mList.add(mMeFragment)
    }

    private fun initBottomNav(){
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changgeFragment(position)
            }

        })
    }

    private fun changgeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        mList.forEach {
            manager.hide(it)
        }
        manager.show(mList.get(position))
        manager.commit()
    }
}