package com.carpal.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Simulate Splash Delay (optional)
        Thread.sleep(1000)

        // Check if user is logged in
        val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // Go to Welcome Screen
            startActivity(Intent(this, WelcomeActivity::class.java))
        } else {
            // Go to Login Screen
            startActivity(Intent(this, LoginActivity::class.java))
        }

        finish()
    }
}
