package com.example.week4luxama

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button1 = findViewById<ImageView>(R.id.imageView)
        val layout1 = findViewById<LinearLayout>(R.id.linear)
        val screen = findViewById<RelativeLayout>(R.id.main)
        val questionplace = findViewById<TextView>(R.id.textView)
        val addbt = findViewById<ImageView>(R.id.imageView2)

        val option1 = findViewById<TextView>(R.id.textView2)
        val option2 = findViewById<TextView>(R.id.textView3)
        val option3 = findViewById<TextView>(R.id.textView4)

        val allOptions = listOf(option1, option2, option3)
        var correctAnswer = option3
        // launcher to get result from AddCardActivity
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                questionplace.text = data?.getStringExtra("updatedQtn")
                option1.text = data?.getStringExtra("answer1")
                option2.text = data?.getStringExtra("answer2")
                option3.text = data?.getStringExtra("answer3")
                val okk = data?.getStringExtra("optioninput")

                correctAnswer = when (okk) {
                    "Answer 1" -> option1
                    "Answer 2" -> option2
                    "Answer 3" -> option3
                    else -> null
                }
            }
        }




        //Function pou mete visib envisib
        fun toggleLayout() {
            if (layout1.isVisible) {
                layout1.visibility = View.INVISIBLE
                button1.setImageResource(R.drawable.myec_foreground)
            } else {
                layout1.visibility = View.VISIBLE
                button1.setImageResource(R.drawable.myeo_foreground)
            }
        }
//Bouton pou rele function vizib envizib la
        button1.setOnClickListener { toggleLayout() }

        fun checkAnswer(correctView: TextView, clickedView: TextView) {
            allOptions.forEach { it.isClickable = false }
            if (clickedView == correctView) {
                clickedView.setBackgroundResource(R.drawable.correctb)
            } else {
                clickedView.setBackgroundResource(R.drawable.incorrectb)
                correctView.setBackgroundResource(R.drawable.correctb)
            }
        }

        option1.setOnClickListener { checkAnswer(correctAnswer, option1) }
        option2.setOnClickListener { checkAnswer(correctAnswer, option2) }
        option3.setOnClickListener { checkAnswer(correctAnswer, option3) }

        //pou Clear repons yo
        fun netwaye(){
            allOptions.forEach {
                it.setBackgroundResource(R.drawable.textde)
                it.isClickable = true
            }

        }

        questionplace.setOnClickListener {
            netwaye()
        }

        //Function pou al nan lot ecran
        fun openAddCard() {
            netwaye()
            layout1.visibility = View.INVISIBLE
            button1.setImageResource(R.drawable.myec_foreground)
            val intent = Intent(this, AddCardActivity::class.java).apply {
                putExtra("qtn", questionplace.text)
                putExtra("opt1", option1.text)
                putExtra("opt2", option2.text)
                putExtra("opt3", option3.text)
            }
            launcher.launch(intent)
        }
//button pou rele function
        addbt.setOnClickListener { openAddCard() }

    }
}