package com.example.brainacademy

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Login : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE)

        val logBtn = findViewById<Button>(R.id.log_btn)
        val loginUsername = findViewById<TextView>(R.id.login_username)
        val loginPassword = findViewById<TextView>(R.id.login_password)

        logBtn.setOnClickListener {
            val usernameLogin: String = loginUsername.text.toString().trim()
            val passwordLogin: String = loginPassword.text.toString().trim()

            if (usernameLogin.isNotEmpty()) {
                database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(usernameLogin).get().addOnSuccessListener {

                    if (it.exists()) {
                        val username = it.child("username").value
                        val password = it.child("password").value

                        if (password == passwordLogin) {
                            // Save login credentials in shared preferences
                            val editor = sharedPreferences.edit()
                            editor.putString("username", username.toString())
                            editor.putString("password", password.toString())
                            editor.apply()

                            Toast.makeText(this, "Welcome to Brain Academy", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SignUp::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_SHORT).show()
            }
        }

        val registerTxt = findViewById<TextView>(R.id.register_txt)
        registerTxt.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // Check if login credentials exist in shared preferences
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)
        if (savedUsername != null && savedPassword != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }














//    private lateinit var binding: ActivityLoginBinding
//    private lateinit var database: DatabaseReference
//    private lateinit var videoView: VideoView
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////
////        binding= ActivityLoginBinding.inflate(layoutInflater)
////        setContentView(binding.root)
//        setContentView(R.layout.activity_login)
//
//
//
//
////        val email_login = findViewById<EditText>(R.id.login_username)
////        val password_login = findViewById<EditText>(R.id.login_password)
////
//        val logBtn = findViewById<Button>(R.id.log_btn)
//
//        val loginUsername = findViewById<TextView>(R.id.login_username)
//        val loginPassword = findViewById<TextView>(R.id.login_password)
//
//        logBtn.setOnClickListener {
//            val usernamelogin:String=loginUsername.text.toString().trim()
//            val passwordlogin:String=loginPassword.text.toString().trim()
//
//            if (usernamelogin.isNotEmpty()){
//                database=FirebaseDatabase.getInstance().getReference("Users")
//                database.child(usernamelogin).get().addOnSuccessListener {
//
//                    if(it.exists()){
//                        var username = it.child("username").value
//                        var password = it.child("password").value
//
//                        if(password==passwordlogin){
//                            Toast.makeText(this,"Welcome to Brain Academy",Toast.LENGTH_LONG).show()
//                            val intent =Intent(this,HomeActivity::class.java)
//                            startActivity(intent)
//                            this.finish()
//
//
//                        }else{
//                            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show()
//                        }
//
//                    }else{
//                        Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this,SignUp::class.java)
//                        startActivity(intent)
//                    }
//                }
//            }else{
//                Toast.makeText(this,"Please enter the username",Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//
//
//        val register_txt = findViewById<TextView>(R.id.register_txt)
//        register_txt.setOnClickListener {
//            val intent = Intent(this, SignUp::class.java)
//            startActivity(intent)
//        }
//
//
////        binding.fab.setOnClickListener { view ->
////            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                .setAction("Action", null).show()
////        }
//    }

}