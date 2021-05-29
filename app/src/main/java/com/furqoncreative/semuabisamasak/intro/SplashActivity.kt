package com.furqoncreative.semuabisamasak.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.furqoncreative.semuabisamasak.R
import com.furqoncreative.semuabisamasak.databinding.ActivitySplashBinding
import com.furqoncreative.semuabisamasak.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Glide.with(applicationContext)
            .load(R.drawable.img_splash)
            .into(binding.ivSplash)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3000)
    }
}