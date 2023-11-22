package com.example.wilapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 4000 // 6 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            // Use a Handler to delay the transition to other pages
            Handler().postDelayed({
                // Start the desired activity
                startActivity(Intent(this, Login::class.java))
                finish() // Close the main activity to prevent going back to it
            }, SPLASH_DELAY)
        }
    }
