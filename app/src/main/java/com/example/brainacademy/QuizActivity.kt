package com.example.brainacademy


import QuizAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainacademy.databinding.ActivityQuizBinding
import com.google.firebase.database.*


class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var optionsAdapter: QuizAdapter
    private lateinit var questionsList: MutableList<questiondatabse>
    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var selectedCategory: String

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the selected category from the intent
      //  selectedCategory = intent.getStringExtra("CATEGORY").toString()

        // Initialize Firebase database
        database = FirebaseDatabase.getInstance().reference.child("Questions")

        // Set up the RecyclerView and adapter
        questionsList = mutableListOf()
        optionsAdapter = QuizAdapter(this, questionsList, R.color.selected_option)

        binding.Quizrecylerview.adapter = optionsAdapter
        binding.Quizrecylerview.layoutManager = LinearLayoutManager(this)

        // Retrieve the questions for the selected category from Firebase database
        database.child("Science")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (subCategorySnapshot in snapshot.children) {
                        for (questionSnapshot in subCategorySnapshot.children) {
                            val questionDescription =
                                questionSnapshot.child("description").getValue(String::class.java)
                            val option1 =
                                questionSnapshot.child("option1").getValue(String::class.java)
                            val option2 =
                                questionSnapshot.child("option2").getValue(String::class.java)
                            val option3 =
                                questionSnapshot.child("option3").getValue(String::class.java)
                            val option4 =
                                questionSnapshot.child("option4").getValue(String::class.java)
                            val correctAnswer =
                                questionSnapshot.child("correctanswer").getValue(String::class.java)
                            val subcategory =
                                questionSnapshot.child("subcategory").getValue(String::class.java)

                            val question = questiondatabse(
                                questionDescription!!,
                                option1!!,
                                option2!!,
                                option3!!,
                                option4!!,
                                correctAnswer!!,
                                subcategory!!
                            )
                            questionsList.add(question)
                        }
                    }
                    optionsAdapter.notifyDataSetChanged()
                    showNextQuestion()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })

        // Set up the click listener for the "Next" button
        // Set up the click listener for the "Next" button
        binding.nextButton.setOnClickListener {
            val selectedOption = optionsAdapter.getSelectedOption(currentQuestionIndex)
            if (selectedOption == null) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            } else {
                if (selectedOption == questionsList[currentQuestionIndex].correctanswer) {
                    score++
                }
                currentQuestionIndex++
                if (currentQuestionIndex < questionsList.size) {
                    showNextQuestion()
                } else {
                    showQuizResult()
                }
            }
        }

        // Function to show the next question


        // Function to show the quiz result

    }
    private fun showQuizResult() {
        val resultPercentage = (score.toFloat() / questionsList.size.toFloat()) * 100
        Toast.makeText(this,"$resultPercentage",Toast.LENGTH_SHORT).show()
//        val resultMessage =
//            getString(R.string.result_message, score, questionsList.size, resultPercentage)
       // val dialog = QuizResultDialog(this, resultMessage)
       // dialog.show()
    }

    private fun showNextQuestion() {
        // Check if we have reached the end of the questions list
        if (currentQuestionIndex == questionsList.size) {
            // Show the final score and end the activity
            Toast.makeText(this, "Your final score is $score", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Set the question description and options in the UI
        val currentQuestion = questionsList[currentQuestionIndex]
        binding.questionTextView.text = currentQuestion.description
        currentQuestion.useranswer?.let { userAnswer ->
            optionsAdapter.setSelectedOption(currentQuestionIndex,userAnswer)
        }

        // Update the question index and reset the user answer
        currentQuestionIndex++
        currentQuestion.useranswer = ""
    }

//    private fun showNextQuestion() {
//        val question = questionsList[currentQuestionIndex]
//        binding.questionTextView.text = question.description
//        optionsAdapter.setOptions(
//            question.option1,
//            question.option2,
//            question.option3,
//            question.option4
//        )
//        binding.questionCountTextView.text =
//            getString(R.string.question_count, currentQuestionIndex + 1, questionsList.size)
//    }
}
