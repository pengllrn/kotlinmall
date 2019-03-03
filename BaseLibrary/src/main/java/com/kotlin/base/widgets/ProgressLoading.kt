package com.kotlin.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.kotlin.base.R
import org.jetbrains.anko.find

/**
 * Author：Pengllrn
 * Date: 2019/3/3
 */
class ProgressLoading private constructor(context: Context,theme: Int) :Dialog(context,theme) {

    companion object {
        private lateinit var mDialog:ProgressLoading
        private var animDrawable:AnimationDrawable? = null

        fun create(context: Context) : ProgressLoading{
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            mDialog.window.attributes.dimAmount = 0.2f

            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    fun showLoading(){
        super.show()
        animDrawable?.start()
    }

    fun hideLoading(){
        super.dismiss()
        animDrawable?.stop()

    }
}