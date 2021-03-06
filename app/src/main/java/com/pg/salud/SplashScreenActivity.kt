package com.pg.salud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    lateinit var imageView: ImageView
    val url = "https://i.pinimg.com/originals/79/c7/ff/79c7ff9d622c8fae535a06898f0d6700.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash)
        imageView = findViewById(R.id.imageView)
        Glide.with(this).load(url).into(imageView)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (firebaseAuth.currentUser != null) {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }

            },
            6000 // value in milliseconds
        )
    }
}