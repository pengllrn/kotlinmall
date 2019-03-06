package com.kotlin.mall.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.provider.common.isLogined
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Author：pengllrn
 * Time: 2019/3/6 20:30
 */
class SettingActivity:BaseActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initView()
    }

    private fun initView() {
        mLogoutBtn.isEnabled = isLogined()

        mLogoutBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mLogoutBtn ->{
                UserPrefsUtils.putUserInfo(null)
                finish()
            }
        }
    }
}