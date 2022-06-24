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
    val url = "https://media2.giphy.com/media/4F1kg9OCP0Lr9n0z9Z/giphy.gif?cid=790b7611340fd7ae6ff9f41d873f0e7dbbebb72a68ade012&rid=giphy.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashbookavo)
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
            3000 // value in milliseconds
        )
    }
}