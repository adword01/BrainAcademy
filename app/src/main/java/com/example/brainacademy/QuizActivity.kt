package com.example.brainacademy


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
//import kotlinx.coroutines.NonCancellable.message

class QuizActivity : AppCompatActivity() {


    private val askedQuestions = mutableListOf<String>()

    private lateinit var database: DatabaseReference
    private lateinit var selected_category: String
    private var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)



       // val nextButton = findViewById<Button>(R.id.nextButton)
        val category = findViewById<TextView>(R.id.category)

        val intent = intent
         selected_category = intent.getStringExtra("category").toString().trim()
//
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

    // Retrieve questions from all subcategories under "Science"
    database.child("Science").addListenerForSingleValueEvent(object : ValueEventListener {
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

                    val question = questiondatabse(questionDescription!!, option1!!, option2!!, option3!!, option4!!,subcategory!!,correctAnswer!!)
                    questionList.add(question)
                }
            }

            // Select a random question that hasn't been asked yet
            val unansweredQuestions = questionList.filter { it.description !in askedQuestions }
            var flag : Int = 0
            if (unansweredQuestions.isNotEmpty()) {
                val randomQuestion = unansweredQuestions.random()
                questionTextView.text = randomQuestion.description
                option1TextView.text = randomQuestion.option1
                option2TextView.text = randomQuestion.option2
                option3TextView.text = randomQuestion.option3
                option4TextView.text = randomQuestion.option4
                subcategoryTextView.text = randomQuestion.subcategory

                // Add the question description to the list of asked questions
                askedQuestions.add(randomQuestion.description)

                // Define selectedOption at the class level
                var selectedOption: String? = null

// Add an onClickListener to each of the option TextViews
                option1TextView.setOnClickListener {
                    selectedOption = option1TextView.text.toString()
                    Toast.makeText(this@QuizActivity,"$selectedOption",Toast.LENGTH_SHORT).show()
                }
                option2TextView.setOnClickListener {
                    selectedOption = option2TextView.text.toString()
                    Toast.makeText(this@QuizActivity,"$selectedOption",Toast.LENGTH_SHORT).show()
                }
                option3TextView.setOnClickListener {
                    selectedOption = option3TextView.text.toString()
                    Toast.makeText(this@QuizActivity,"$selectedOption",Toast.LENGTH_SHORT).show()
                }
                option4TextView.setOnClickListener {
                    selectedOption = option4TextView.text.toString()
                    Toast.makeText(this@QuizActivity,"$selectedOption",Toast.LENGTH_SHORT).show()
                }

// Add an onClickListener to the nextButton
                val nextButton = findViewById<Button>(R.id.nextButton)
                nextButton.setOnClickListener {
                    // Check the selected answer and update the score
                    checkAnswer(selectedOption.toString(), randomQuestion.correctanswer)
                    // Clear the selected option variable
//                    selectedOption = null
                    // Display the next question or show the final score
                    if (askedQuestions.size < questionList.size) {
                        displayQuestions()
                    }
                    if (askedQuestions.size >= questionList.size) {
                        showFinalScore()
                    }
//                    else {
//                        Toast.makeText(this@QuizActivity,"SCore",Toast.LENGTH_SHORT).show()
////                        showFinalScore()
//                    }

                }

            } else {
//                ++flag
//             //   Toast.makeText(this@QuizActivity,"Toast",Toast.LENGTH_SHORT).show()
//                if(flag == 1){

//                    showFinalScore()
//                }
                // All questions have been asked, exit the activity
             //   val intent = Intent(this@QuizActivity,ResultActivity::class.java)
                finish()

            }

        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(this@QuizActivity, "Failed to retrieve questions for $selected_category", Toast.LENGTH_SHORT).show()
        }
    })


}


      // Function to check if the selected option is correct and update the score
    private fun checkAnswer(selectedOption: String, correctAnswer: String?) {

        if (selectedOption.toString().trim() == correctAnswer.toString().trim()) {
            score++
        }
        displayQuestions()
    }

    fun showFinalScore() {
//        val message = "Your final score is $score "
        Toast.makeText(this@QuizActivity, "$score", Toast.LENGTH_LONG).show()

        // You can also start a new activity to show the final score in a different screen
    }


}
