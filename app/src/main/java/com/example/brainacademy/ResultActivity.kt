package com.example.brainacademy

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ResultActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = intent
        val score = intent.getIntExtra("score", 0)
        val totalScore = score.toString()

        // Get the username from the shared preferences
        val sharedPreferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username", "")!!

        database = FirebaseDatabase.getInstance().getReference("Users")

        // Update the high score in the database if the current score is higher
        database.child(username).child("highscore").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val highScore = snapshot.getValue(Int::class.java) ?: 0
                if (score >= highScore) {
                    database.child(username).child("highscore").setValue(score)
                } else {
                    database.child(username).child("highscore").setValue(highScore)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        val scoreTxt = findViewById<TextView>(R.id.score_txt)
        scoreTxt.text = "$totalScore out of 10"

        val finishQuizBtn = findViewById<Button>(R.id.finish_quiz)
        finishQuizBtn.setOnClickListener {
            val intent = Intent(this@ResultActivity, HomeActivity::class.java)
            intent.putExtra("prev_score", score)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "ResultActivity"
    }









//    private lateinit var database: DatabaseReference
//    private lateinit var username: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
//
//        val intent = intent
//        val score = intent.getIntExtra("score", 0)
//        val totalScore = score.toString()
//
//        // Get the username from the shared preferences
//        val sharedPreferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
//        username = sharedPreferences.getString("username", "")!!
//
//        database = FirebaseDatabase.getInstance().getReference("Users")
//
//        // Update the high score in the database if the current score is higher
//        database.child(username).child("highscore").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val highScore = snapshot.getValue(Int::class.java) ?: 0
//                if (score > highScore) {
//                    database.child(username).child("highscore").setValue(score)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
//
//        val scoreTxt = findViewById<TextView>(R.id.score_txt)
//        scoreTxt.text = "$totalScore out of 10"
//
//        val finishQuizBtn = findViewById<Button>(R.id.finish_quiz)
//        finishQuizBtn.setOnClickListener {
//            val intent = Intent(this@ResultActivity, HomeActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    companion object {
//        private const val TAG = "ResultActivity"
//    }
}


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
//
//        val intent = intent
//        val score = intent.getIntExtra("score", 0)
//        val totalScore = score.toString()
//
//        database = FirebaseDatabase.getInstance().getReference("Users")
//
//        // Update the high score in the database if the current score is higher
//        database.child("username").child("highscore").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val highScore = snapshot.getValue(Int::class.java) ?: 0
//                if (score > highScore) {
//                    database.child("highscore").setValue(score)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
//
//        val scoreTxt = findViewById<TextView>(R.id.score_txt)
//        scoreTxt.text = "$totalScore out of 10"
//
//        val finishQuizBtn = findViewById<Button>(R.id.finish_quiz)
//        finishQuizBtn.setOnClickListener {
//            val intent = Intent(this@ResultActivity, HomeActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//
//
//
//
//
//    companion object {
//        private const val TAG = "ResultActivity"
//    }
//}










//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//
//class ResultActivity : AppCompatActivity() {
//
//    private lateinit var database: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
//
//        val intent = intent
//        val score = intent.getIntExtra("score", 0).toString()
//
//        database= FirebaseDatabase.getInstance().getReference("Users")
//
//        val total_score = score
//
//        database.child("score").setValue(total_score)
//
//        val score_txt = findViewById<TextView>(R.id.score_txt)
////        val final_score = intent.getIntExtra("Score",0)
////
//        score_txt.setText(score + " out of 10").toString()
//
//        val finish_quiz = findViewById<Button>(R.id.finish_quiz)
//        finish_quiz.setOnClickListener {
//            val intent = Intent(this@ResultActivity,HomeActivity::class.java)
//            startActivity(intent)
//        }
//
//    }
//}