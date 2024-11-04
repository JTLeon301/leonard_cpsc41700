package com.example.jtleon.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //initialize variables
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton=findViewById(R.id.true_button)
        trueButton.setOnClickListener{ view : View ->
            Toast.makeText(this,R.string.correct, Toast.LENGTH_SHORT).show()
        }
        falseButton=findViewById(R.id.false_button)
        falseButton.setOnClickListener{view : View ->
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show()
        }
    }
}