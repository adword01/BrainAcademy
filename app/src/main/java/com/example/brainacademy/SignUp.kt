package com.example.brainacademy

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var database :DatabaseReference
    private lateinit var cb: CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


//        var name = findViewById<EditText>(R.id.name_txt)
//        var email = findViewById<EditText>(R.id.email_txt)
//        var username = findViewById<EditText>(R.id.username_txt)
//        var password = findViewById<EditText>(R.id.password_txt)




        val loginTxt = findViewById<TextView>(R.id.login_txt)
        loginTxt.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


        val registerBtn = findViewById<Button>(R.id.register_btn)
        registerBtn.setOnClickListener {

//                if (name.length() < 2) {
//                    Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show()
//                }
//                if (Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()) {
//                    Toast.makeText(this, "Mail is valid", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
//                }
//                if (username.isAllCaps) {
//                    Toast.makeText(this, "Please use smallcase for username", Toast.LENGTH_SHORT)
//                        .show()
//                }
//                if (password.length() < 6) {
//                    Toast.makeText(this, "Please enter a strong password", Toast.LENGTH_SHORT)
//                        .show()
//                } else
//                {

//                if (cb.isSelected) {

            val nameTxt = findViewById<EditText>(R.id.name_txt)
            val emailTxt = findViewById<EditText>(R.id.email_txt)
            val usernameTxt = findViewById<EditText>(R.id.username_txt)
            val passwordTxt = findViewById<EditText>(R.id.password_txt)

            val name = nameTxt.text.toString()
            val email = emailTxt.text.toString()
            val username = usernameTxt.text.toString()
            val password = passwordTxt.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name, email, username, password)
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

//                }
//                else{
//                    Toast.makeText(this,"Please accept all terms and Conditions*.",Toast.LENGTH_SHORT).show()
//                }
//            }

        }
    }
}