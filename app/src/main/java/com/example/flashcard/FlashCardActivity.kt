package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class FlashCardActivity : AppCompatActivity() {
    private val problems = mutableListOf<String>()
    private var currentProblemIndex = 0
    private var score = 0
    private var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)

        val username = intent.getStringExtra("username")
        Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()

        val generateButton = findViewById<Button>(R.id.generate_button)
        val submitButton = findViewById<Button>(R.id.submit_button)
        val answerEditText = findViewById<EditText>(R.id.answer_edit_text)

        generateButton.setOnClickListener {
            if (!gameStarted) {
                generateProblems()
                gameStarted = true
                disableGenerateButton()
                loadNextProblem()
            }
        }

        submitButton.setOnClickListener {
            if (gameStarted) {
                val userAnswer = answerEditText.text.toString()
                if (currentProblemIndex < problems.size) {
                    val correctAnswer = evaluateProblem(problems[currentProblemIndex])
                    if (userAnswer == correctAnswer.toString()) {
                        score++
                    }
                    answerEditText.text.clear()
                    currentProblemIndex++
                    if (currentProblemIndex < problems.size) {
                        loadNextProblem()
                    } else {
                        endGame()
                    }
                }
            }
        }
    }

    private fun generateProblems() {
        problems.clear()
        for (i in 0 until 10) {
            val isAddition = Random.nextBoolean()
            val operand1 = Random.nextInt(1, 100)
            val operand2 = Random.nextInt(1, 21)
            val problem = if (isAddition) {
                "$operand1 + $operand2"
            } else {
                "$operand1 - $operand2"
            }
            problems.add(problem)
        }
    }

    private fun loadNextProblem() {
        val problemTextView = findViewById<TextView>(R.id.problem_text_view)
        problemTextView.text = problems[currentProblemIndex]
    }

    private fun evaluateProblem(problem: String): Int {
        val parts = problem.split(" ")
        val operand1 = parts[0].toInt()
        val operator = parts[1]
        val operand2 = parts[2].toInt()
        return if (operator == "+") {
            operand1 + operand2
        } else {
            operand1 - operand2
        }
    }

    private fun endGame() {
        gameStarted = false
        enableGenerateButton()
        currentProblemIndex = 0
        Toast.makeText(this, "Score: $score out of 10", Toast.LENGTH_SHORT).show()
        score = 0
    }

    private fun disableGenerateButton() {
        val generateButton = findViewById<Button>(R.id.generate_button)
        generateButton.isEnabled = false
    }

    private fun enableGenerateButton() {
        val generateButton = findViewById<Button>(R.id.generate_button)
        generateButton.isEnabled = true
    }



}