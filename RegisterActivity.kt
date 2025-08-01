package com.carpal.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var nameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEt = findViewById(R.id.nameInput)
        emailEt = findViewById(R.id.emailInput)
        passwordEt = findViewById(R.id.passwordInput)
        registerBtn = findViewById(R.id.registerButton)

        registerBtn.setOnClickListener {
            val name = nameEt.text.toString().trim()
            val email = emailEt.text.toString().trim()
            val password = passwordEt.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$".toRegex()

            if (!email.matches(emailRegex)) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!password.matches(passwordRegex)) {
                Toast.makeText(
                    this,
                    "Password must have 6+ characters, include upper, lower, digit",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
            sharedPref.edit()
                .putString("name", name)
                .putString("email", email)
                .putString("password", password)
                .apply()

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
            finish() // Go back to Login
        }
    }
}
