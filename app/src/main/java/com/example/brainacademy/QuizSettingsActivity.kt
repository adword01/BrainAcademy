package com.example.brainacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizSettingsActivity : AppCompatActivity() {

    private lateinit var categorySpinner: Spinner
//    private lateinit var subcategorySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_settings)

//        val category = findViewById<Spinner>(R.id.category_spinner)



        // Get the length of the quiz, category, and subcategory from the previous activity
        val quizLength = intent.getIntExtra("quizLength", 0)
        val category = intent.getStringExtra("category")


//        val bundle = Bundle()
//        bundle.putString("id" ,categorySpinner.selectedItem.toString())

//        val subcategory = intent.getStringExtra("subcategory")

// Get a reference to the Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()
        val questionsRef = database.getReference("Question Database")

// Query the database for questions that match the category and subcategory
        val query = questionsRef.orderByChild("category_subcategory").equalTo("subcategory")

// Add a listener to the query to retrieve the questions and start the quiz
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val questions = ArrayList<Questions>()
                for (questionSnapshot in snapshot.children) {
                    val question = questionSnapshot.getValue(Questions::class.java)
                    if (question != null) {
                        questions.add(question)
                    }
                }
                // Shuffle the questions
                questions.shuffle()

                // Take only the number of questions specified by the quiz length
                val quizQuestions = questions.subList(0, quizLength)

                // Start the quiz activity and pass the list of questions as an extra
//                val quizIntent = Intent(this@QuizSettingsActivity, MainActivity::class.java)
////                quizIntent.putExtra("Question", quizQuestions)
//                startActivity(quizIntent)

            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
            }
        })



        val quizLengthRadioGroup = findViewById<RadioGroup>(R.id.quiz_length_radio_group)
        categorySpinner = findViewById(R.id.category_spinner)
//        subcategorySpinner = findViewById(R.id.subcategory_spinner)

        // Set up the quiz length radio group
        quizLengthRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedQuizLength = when (checkedId) {
                R.id.radio_btn_5 -> 5
                R.id.radio_btn_10 -> 10
                R.id.radio_btn_15 -> 15
                R.id.radio_btn_20 -> 20
                else -> 10 // Default quiz length
            }

            // Do something with the selected quiz length
            Log.d(TAG, "Selected quiz length: $selectedQuizLength")
        }

        // Set up the category spinner
        val categories = arrayOf("Select Category","Science", "Technology", "Engineering","Mathematics","Entrepreneurship") // Replace with your own data
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCategory = categories[position]

                // Do something with the selected category
//                Log.d(TAG, "Selected category: $selectedCategory")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        val generate_btn=findViewById<Button>(R.id.generate_quiz)
        generate_btn.setOnClickListener {

            val intent = Intent(this,QuizActivity::class.java)
            val selected_category = categorySpinner.selectedItem.toString()

            Log.d(TAG, "Selected category: $selected_category")
            intent.putExtra("category", selected_category)
            intent.putExtra("length", quizLength)
            startActivity(intent)
        }
        // Set up the subcategory spinner
//        val subcategories = arrayOf("Select Subcategory","Physics", "Chemistry", "Biology","Earth Science","Physical Science"
//        ,"System Administration", "Network Security", "Software Development","Computer Science", "Information Technology", "Electronics","Mechanical"
//        ,"Applied Mathematics", "Statistics", "Data Science","Cryptography","Probability","General Awareness", "Finance", "Leadership","Books","Marketing","R&d")
////        val  = arrayOf("Physics", "Chemistry", "Biology","Earth Science","Physical Science")
////        val innerSpinnerArray3 = arrayOf("System Administration", "Network Security", "Software Development")
////        val innerSpinnerArray4 = arrayOf("Computer Science", "Information Technology", "Electronics","Mechanical")
////        val innerSpinnerArray5 = arrayOf("Applied Mathematics", "Statistics", "Data Science","Cryptography","Probability")
////        val innerSpinnerArray6 = arrayOf("General Awareness", "Finance", "Leadership","Books","Marketing","R&d")
//
//        val subcategoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subcategories)
//        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        subcategorySpinner.adapter = subcategoryAdapter
//        subcategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//                val selectedSubcategory = subcategories[position]
//
//                // Do something with the selected subcategory
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // Do nothing
//            }
//        }
    }

    companion object {
        private const val TAG = "QuizSettingsActivity"
    }
}
