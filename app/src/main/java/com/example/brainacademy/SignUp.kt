package com.example.brainacademy

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var database :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val loginTxt = findViewById<TextView>(R.id.login_txt)
        loginTxt.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


        val registerBtn = findViewById<Button>(R.id.register_btn)
        registerBtn.setOnClickListener {


            val nameTxt = findViewById<EditText>(R.id.name_txt)
            val emailTxt = findViewById<EditText>(R.id.email_txt)
            val usernameTxt = findViewById<EditText>(R.id.username_txt)
            val passwordTxt = findViewById<EditText>(R.id.password_txt)

            val name = nameTxt.text.trim().toString()
            val email = emailTxt.text.trim().toString()
            val username = usernameTxt.text.trim().toString()
            val password = passwordTxt.text.trim().toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name, email, username, password, highscore=0, lastscore=0)
            database.child(username).setValue(User).addOnSuccessListener {
                nameTxt.text?.clear()
                emailTxt.text?.clear()
                usernameTxt.text?.clear()
                passwordTxt.text?.clear()

                Toast.makeText(
                    this,
                    "Successfully Subscribed to the academy",
                    Toast.LENGTH_SHORT
                ).show()


            }.addOnFailureListener {
                Toast.makeText(this, "😥 Wrong credentials", Toast.LENGTH_SHORT).show()

            }

        }
    }
}