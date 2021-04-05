package com.anubys.example.mapping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.anubys.example.mapping.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent= Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}
