package com.example.brainacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class QuizActivity : AppCompatActivity() {

    private var askedQuestions = mutableListOf<String>()
    var flag: Boolean = true
    private lateinit var database: DatabaseReference
    private lateinit var selected_category: String
    private var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val category = findViewById<TextView>(R.id.category)

        val intent = intent
        selected_category = intent.getStringExtra("category").toString().trim()
        category.setText(selected_category)
        displayQuestions()
    }
    private fun displayQuestions() {
        database = FirebaseDatabase.getInstance().reference.child("Questions")
        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val option1TextView = findViewById<TextView>(R.id.option1)
        val option2TextView = findViewById<TextView>(R.id.option2)
        val option3TextView = findViewById<TextView>(R.id.option3)
        val option4TextView = findViewById<TextView>(R.id.option4)
        val subcategoryTextView = findViewById<TextView>(R.id.subcategory)

        database.child(selected_category).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val questionList = mutableListOf<questiondatabse>()
                for (subCategorySnapshot in snapshot.children) {
                    for (questionSnapshot in subCategorySnapshot.children) {
                        val questionDescription = questionSnapshot.child("description").getValue(String::class.java)
                        val option1 = questionSnapshot.child("option1").getValue(String::class.java)
                        val option2 = questionSnapshot.child("option2").getValue(String::class.java)
                        val option3 = questionSnapshot.child("option3").getValue(String::class.java)
                        val option4 = questionSnapshot.child("option4").getValue(String::class.java)
                        val subcategory = questionSnapshot.child("subcategory").getValue(String::class.java)
                        val correctAnswer = questionSnapshot.child("correctanswer").getValue(String::class.java)

                        val question = questiondatabse(
                            questionDescription!!,
                            option1!!,
                            option2!!,
                            option3!!,
                            option4!!,
                            subcategory!!,
                            correctAnswer!!
                        )
                        questionList.add(question)
                    }
                }

                // Shuffle the list of questions and take the first 10 questions
                val shuffledQuestionList = questionList.shuffled()
                val selectedQuestions = shuffledQuestionList.take(10)

                val unansweredQuestions = selectedQuestions.filter { it.description !in askedQuestions }

                if (unansweredQuestions.isNotEmpty()) {
                    val randomQuestion = unansweredQuestions.random()
                    questionTextView.text = randomQuestion.description
                    option1TextView.text = randomQuestion.option1
                    option2TextView.text = randomQuestion.option2
                    option3TextView.text = randomQuestion.option3
                    option4TextView.text = randomQuestion.option4
                    subcategoryTextView.text = randomQuestion.subcategory
                    askedQuestions.add(randomQuestion.description)

                    var selectedOptionTextView: TextView? = null
                    var selectedOption: String? = null

                    option1TextView.setOnClickListener {
                        if (selectedOptionTextView != option1TextView) {
                            selectedOptionTextView?.setBackgroundResource(R.drawable.default_option_background)
                            selectedOptionTextView = option1TextView
                            selectedOption = option1TextView.text.toString()
                            option1TextView.setBackgroundResource(R.drawable.selected_option_border)
                        } else {
                            selectedOptionTextView = null
                            selectedOption = null
                            option1TextView.setBackgroundResource(R.drawable.default_option_background)
                        }
                    }

                    option2TextView.setOnClickListener {
                        if (selectedOptionTextView != option2TextView) {
                            selectedOptionTextView?.setBackgroundResource(R.drawable.default_option_background)
                            selectedOptionTextView = option2TextView
                            selectedOption = option2TextView.text.toString()
                            option2TextView.setBackgroundResource(R.drawable.selected_option_border)
                        } else {
                            selectedOptionTextView = null
                            selectedOption = null
                            option2TextView.setBackgroundResource(R.drawable.default_option_background)
                        }
                    }

                    option3TextView.setOnClickListener {
                        if (selectedOptionTextView != option3TextView) {
                            selectedOptionTextView?.setBackgroundResource(R.drawable.default_option_background)
                            selectedOptionTextView = option3TextView
                            selectedOption = option3TextView.text.toString()
                            option3TextView.setBackgroundResource(R.drawable.selected_option_border)
                        } else {
                            selectedOptionTextView = null
                            selectedOption = null
                            option3TextView.setBackgroundResource(R.drawable.default_option_background)
                        }
                    }

                    option4TextView.setOnClickListener {
                        if (selectedOptionTextView != option4TextView) {
                            selectedOptionTextView?.setBackgroundResource(R.drawable.default_option_background)
                            selectedOptionTextView = option4TextView
                            selectedOption = option4TextView.text.toString()
                            option4TextView.setBackgroundResource(R.drawable.selected_option_border)
                        } else {
                            selectedOptionTextView = null
                            selectedOption = null
                            option4TextView.setBackgroundResource(R.drawable.default_option_background)
                        }
                    }

                    val nextButton = findViewById<Button>(R.id.nextButton)
                    nextButton.setOnClickListener {
                        if (selectedOption != null) {
                            checkAnswer(selectedOption!!, randomQuestion.correctanswer)

                            selectedOptionTextView?.setBackgroundResource(R.drawable.default_option_background)
                            selectedOptionTextView = null
                            selectedOption = null
                            if (askedQuestions.size == 10) {
                                showFinalScore()
                                val intent = Intent(this@QuizActivity,ResultActivity::class.java)
                                intent.putExtra("score", score)
                                startActivity(intent)
                                finish()
                            } else {
                                displayQuestions()
                            }
                        } else {
                            Toast.makeText(this@QuizActivity, "Please select an answer.", Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    showFinalScore()
                    val intent = Intent(this@QuizActivity,ResultActivity::class.java)
                    intent.putExtra("score", score)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@QuizActivity,
                    "Failed to retrieve questions for $selected_category",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    private fun checkAnswer(selectedOption: String, correctAnswer: String?) {
        if (selectedOption.toString().trim() == correctAnswer.toString().trim()) {
            score++
        }
        displayQuestions()
    }
    fun showFinalScore() {
        Toast.makeText(this@QuizActivity, "Your Total Score is : $score", Toast.LENGTH_LONG).show()
        val intent = Intent(this@QuizActivity,ResultActivity::class.java)
        intent.putExtra("Score",score)
        finish()
    }
}