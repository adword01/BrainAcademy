package com.example.brainacademy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ScoreActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Get the username from SharedPreferences
        val sharedPreferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val score_user = findViewById<TextView>(R.id.score_user)
        score_user.text="Congrats!! " +"$username"

        val home_btn=findViewById<Button>(R.id.score_to_home)
        home_btn.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        // Retrieve the high score from Firebase Realtime Database
        database = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = database.child(username!!)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val highscore = dataSnapshot.child("highscore").getValue()
                    val previous= dataSnapshot.child("lastscore").getValue()
                    val hs = findViewById<TextView>(R.id.high_score)
                    hs.text = highscore.toString()
                    val prev = findViewById<TextView>(R.id.previous_score)
                    prev.text = previous.toString()
                } else {
                    Log.e("firebase", "High score not found for user $username")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("firebase", "Error getting high score for user $username", databaseError.toException())
            }
        })

        // Set the previous score in the activity
//        val intent = intent
//        val prevscore = intent.getIntExtra("prev_score", 0)

    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_score)
//
//        database = FirebaseDatabase.getInstance().getReference("Users")
//
//        database.child("username").child("highscore").get().addOnSuccessListener {
//                val high = it.child("highscore").getValue()
//            val hs=findViewById<TextView>(R.id.high_score)
//            hs.text="$high"
//
//
//        //            Log.i("firebase", "Got value ${it.value}")
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }
//
//        val intent = intent
//        val prevscore = intent.getIntExtra("prev_score", 0)
//
//        val prev=findViewById<TextView>(R.id.previous_score)
//        prev.text="$prevscore"
//    }
}