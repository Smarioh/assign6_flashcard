package com.example.flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            // Simulate login with hardcoded credentials
            val username = "your_username"
            val password = "your_password"

            if (validateLogin(username, password)) {
                val intent = Intent(this, FlashCardActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        // Implement your authentication logic here
        return username == "your_username" && password == "your_password"
    }
}
