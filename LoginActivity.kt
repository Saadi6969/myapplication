package com.carpal.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.emailInput)
        passwordEt = findViewById(R.id.passwordInput)
        loginBtn = findViewById(R.id.loginButton)
        registerTv = findViewById(R.id.registerText)

        loginBtn.setOnClickListener {
            val email = emailEt.text.toString().trim()
            val password = passwordEt.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
            val storedEmail = sharedPref.getString("email", "")
            val storedPassword = sharedPref.getString("password", "")

            if (email == storedEmail && password == storedPassword) {
                sharedPref.edit().putBoolean("isLoggedIn", true).apply()
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        registerTv.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
