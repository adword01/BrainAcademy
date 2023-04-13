package com.example.brainacademy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)

        val logout= findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            val editor = sharedPreferences.edit()

            editor.putString("username",null )
            editor.putString("password",null )
            editor.apply()

            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}