package com.example.week4luxama

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
val questi= getIntent().getStringExtra("qtn")
        val backHomeBtn = findViewById<ImageView>(R.id.backHomeBtn)
        val savebutton = findViewById<ImageView>(R.id.savebutton)

        val inputQuestion = findViewById<EditText>(R.id.question)
        val answer1 = findViewById<EditText>(R.id.answer1)
        val answer2 = findViewById<EditText>(R.id.answer2)
        val answer3 = findViewById<EditText>(R.id.answer3)
        val answerSelector = findViewById<Spinner>(R.id.answerSelector)

//        inputQuestion.setText(questi)
        fun closeThis() {
            finish()
        }

        fun onSubmit() {
            if (inputQuestion.text.toString().isEmpty() ||
                answer1.text.toString().isEmpty() ||
                answer2.text.toString().isEmpty() ||
                answer3.text.toString().isEmpty()
            ) {
                val errorFields = listOf(inputQuestion, answer1, answer2, answer3)
                errorFields.forEach { it.hint = "Sa paka vid" } // "Cannot be empty"
            } else {
                val optionChoice = answerSelector.selectedItem.toString()
                val resultIntent = Intent()
                resultIntent.putExtra("updatedQtn", inputQuestion.text.toString())
                resultIntent.putExtra("answer1", answer1.text.toString())
                resultIntent.putExtra("answer2", answer2.text.toString())
                resultIntent.putExtra("answer3", answer3.text.toString())
                resultIntent.putExtra("optioninput",optionChoice)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        savebutton.setOnClickListener {
            onSubmit()
        }

        backHomeBtn.setOnClickListener {
            closeThis()
        }

    }
}