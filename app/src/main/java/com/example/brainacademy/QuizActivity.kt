package com.example.brainacademy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference

class QuizActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
//    private lateinit var category: String
//    private lateinit var binding: ActivityQuizBinding

//
//    private var questionList: MutableList<Questions> = mutableListOf()
//    private var currentQuestionIndex = 0
//    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
//        setContentView(binding.root)
//        binding = ActivityQuizBinding.inflate(layoutInflater)



        val category = findViewById<TextView>(R.id.category)

        val intent = intent
        val selected_category = intent.getStringExtra("category")

        category.setText(selected_category)

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val selected_category = intent.getStringExtra("category")
            if (selected_category != null) {
                if (selected_category.isNotEmpty()){
//                    displayQuestion(selected_category)
                }
            }
        }

//
//
//            // Get category and quiz length from previous activity
//            val category = intent.getStringExtra("category").toString()
            val quizLength = intent.getIntExtra("length", 0)
//
//            // Set up Firebase database reference





//            // Retrieve questions from database for selected category
//            database.child(category).addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (questionSnapshot in snapshot.children) {
//                        val question = questionSnapshot.getValue(Questions::class.java)
//                        if (question != null) {
//                            questionList.add(question)
//                        }
//                    }
//                    startQuiz(quizLength)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.e("QuizActivity", "Error retrieving questions: ${error.message}")
//                }
//            })
//        }
//
//        private fun startQuiz(quizLength: Int) {
//            // Shuffle questions
//            questionList.shuffle()
//
//            // Display first question
//            displayQuestion()
////            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
//
//            // Set up radio button listener
//            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
//                val selectedOption = group.findViewById<RadioButton>(checkedId)
//                val answer = selectedOption.text.toString()
//                checkAnswer(answer)
//            }
//
//            // Set up next button listener
//            binding.nextButton.setOnClickListener {
//                if (currentQuestionIndex < quizLength - 1) {
//                    currentQuestionIndex++
//                    displayQuestion()
//                } else {
//                    endQuiz()
//                }
//            }
//        }
//

//
//        private fun checkAnswer(answer: String) {
//            val question = questionList[currentQuestionIndex]
//            if (question.correctOption == answer) {
//                score++
//            }
//        }
//
//        private fun endQuiz() {
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("SCORE", score)
//            intent.putExtra("LENGTH", questionList.size)
//            startActivity(intent)
//            finish()
//        }
    }
//    private fun displayQuestion(selected_category: String) {
//        val questionTextView = findViewById<TextView>(R.id.questionTextView)
//        val option1 = findViewById<TextView>(R.id.option1)
//        val option2 = findViewById<TextView>(R.id.option2)
//        val option3 = findViewById<TextView>(R.id.option3)
//        val option4 = findViewById<TextView>(R.id.option4)
//
//        database = FirebaseDatabase.getInstance().getReference("Question Database")
//                database.child(selected_category).get().addOnSuccessListener {
//                    if (it.exists()) {
//                        val ques = it.child("question").value
//                        val op1 = it.child("option1").value
//                        val op2 = it.child("option2").value
//                        val op3 = it.child("option3").value
//                        val op4 = it.child("option4").value
//
//                        questionTextView.text = ques.toString()
//                        option1.text = op1.toString()
//                        option2.text = op2.toString()
//                        option3.text = op3.toString()
//                        option4.text = op4.toString()
//                    } else {
//                        Toast.makeText(this, "Select a valid category", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                }
//

}
