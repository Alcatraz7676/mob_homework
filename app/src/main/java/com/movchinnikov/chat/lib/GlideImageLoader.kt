package com.movchinnikov.chat.lib

import android.content.Context
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class GlideImageLoader(context: Context) : ImageLoader {
    private val glideRequestManager: RequestManager = Glide.with(context)

    override fun load(imageView: ImageView, URL: String) {
        glideRequestManager.load(URL).into(imageView)
    }
}
