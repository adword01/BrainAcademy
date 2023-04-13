package com.example.brainacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddNewQuestionsActivity : AppCompatActivity() {


        private lateinit var questionEditText: EditText
        private lateinit var option1EditText: EditText
        private lateinit var option2EditText: EditText
        private lateinit var option3EditText: EditText
        private lateinit var option4EditText: EditText
        private lateinit var addButton: Button
        private lateinit var database: FirebaseDatabase

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_add_new_questions)

            // Initialize views
            questionEditText = findViewById(R.id.questionEditText)
            option1EditText = findViewById(R.id.option1EditText)
            option2EditText = findViewById(R.id.option2EditText)
            option3EditText = findViewById(R.id.option3EditText)
            option4EditText = findViewById(R.id.option4EditText)
            addButton = findViewById(R.id.addQuestionButton)

            // Initialize Firebase database
            database = FirebaseDatabase.getInstance()

            // Set click listener for the Add button
            addButton.setOnClickListener {
                // Get input values
                val question = questionEditText.text.toString()
                val options = arrayListOf<String>(
                    option1EditText.text.toString(),
                    option2EditText.text.toString(),
                    option3EditText.text.toString(),
                    option4EditText.text.toString()
                )

                // Validate input
                if (question.isEmpty()) {
                    questionEditText.error = "Please enter a question"
                    return@setOnClickListener
                }
                if (options.contains("")) {
                    Toast.makeText(this, "Please enter all options", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Generate a unique ID for the new question
                val questionId = database.reference.child("questions").push().key

                // Create a Question object and save it in the database
                val newQuestion = Question(question,options,0)
                database.reference.child("Questions").child(questionId!!).setValue(newQuestion)

                // Clear input fields
                questionEditText.text.clear()
                option1EditText.text.clear()
                option2EditText.text.clear()
                option3EditText.text.clear()
                option4EditText.text.clear()

                Toast.makeText(this, "Question added successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

