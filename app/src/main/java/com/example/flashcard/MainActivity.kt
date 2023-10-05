package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        // Set a click listener for the "Start Game" button
        startGameButton.setOnClickListener {
            // Launch the LoginActivity when the button is clicked
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}