package com.example.contentfinder.Controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

import com.example.contentfinder.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
        },1)
    }
}
