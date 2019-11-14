package com.movchinnikov.chat

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.movchinnikov.chat.lib.GlideImageLoader
import com.movchinnikov.chat.lib.ImageLoader

class ChatApplication : Application() {
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        setupFirebase()
        setupImageLoader()
    }

    private fun setupImageLoader() {
        imageLoader = GlideImageLoader(this)
    }

    private fun setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}