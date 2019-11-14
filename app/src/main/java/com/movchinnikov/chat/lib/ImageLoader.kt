package com.movchinnikov.chat.lib

import android.widget.ImageView

interface ImageLoader {
    fun load(imageView: ImageView, URL: String)
}
