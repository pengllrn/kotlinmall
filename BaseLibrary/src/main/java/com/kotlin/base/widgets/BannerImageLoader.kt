package com.kotlin.base.widgets

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Author：pengllrn
 * Time: 2019/3/5 23:25
 */
class BannerImageLoader:ImageLoader() {
    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        GlideUtils.loadUrlImage(context,path.toString(),imageView)
    }
}