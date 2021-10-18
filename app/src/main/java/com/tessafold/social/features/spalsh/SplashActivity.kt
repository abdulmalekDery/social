package com.tessafold.social.features.spalsh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.tessafold.social.features.posts.PostsActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, PostsActivity::class.java))
        finish()
    }
}