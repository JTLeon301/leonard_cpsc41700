package com.example.jtleon.courserating

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jtleon.courserating.ui.theme.CourseRatingTheme

//initialize constant
const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    //initialize variables
    private lateinit var courseRating: CourseRating
    private lateinit var courseNameEditText: EditText
    private lateinit var instructorNameEditText: EditText
    private lateinit var courseTypeSpinner: Spinner
    private lateinit var ratingBar: RatingBar
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseRating = CourseRating()

        //course name
        courseNameEditText = findViewById(R.id.course_edit_text)
        courseNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //not do
            }

            override fun onTextChanged(seq: CharSequence?, start: Int, count: Int, after: Int) {
                courseRating.courseName = seq.toString()
                Log.d(TAG, "update course name to" + seq.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                //not do
            }
        })

        //instructor name
        instructorNameEditText = findViewById(R.id.instructor_edit_text)
        instructorNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //not do
            }

            override fun onTextChanged(seq: CharSequence?, start: Int, count: Int, after: Int) {
                courseRating.instructorName = seq.toString()
                Log.d(TAG, "update instructor name to" + seq.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                //not do
            }
        })

        //course type
        courseTypeSpinner = findViewById(R.id.spinner)
        courseTypeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView <*>?, view: View?, pos: Int, id: Long) {
               val courseType = parent?.getItemAtPosition(pos) as String
                courseRating.courseType = courseType
                Log.d(TAG, "course type $courseType")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //do nothing
            }
        }

        //rating bar
        ratingBar = findViewById(R.id.ratingBar)
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener{parent, rating, fromUser ->
                courseRating.rating = rating.toInt()
                Log.d(TAG, "rating $rating")
        }
        submitButton = findViewById(R.id.submit_button)
    }

    fun onSubmitButtonClick(view: View) {
        Toast.makeText(applicationContext,
            courseRating.toString(),
            Toast.LENGTH_SHORT).show()
    }
}