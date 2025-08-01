package com.carpal.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var welcomeText: TextView
    private lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcomeText = findViewById(R.id.welcomeText)
        logoutBtn = findViewById(R.id.logoutButton)

        val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
        val userName = sharedPref.getString("name", "User")

        welcomeText.text = "Welcome, $userName!"

        logoutBtn.setOnClickListener {
            sharedPref.edit().putBoolean("isLoggedIn", false).apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
