package com.example.brainacademy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.brainacademy.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Login : AppCompatActivity() {

//    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DatabaseReference
    private lateinit var videoView: VideoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        binding= ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_login)

        videoView = findViewById(R.id.login_video)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.login_vid
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()

        videoView.setOnPreparedListener {
            it.isLooping = true
        }


//        val email_login = findViewById<EditText>(R.id.login_username)
//        val password_login = findViewById<EditText>(R.id.login_password)
//
        val logBtn = findViewById<Button>(R.id.log_btn)

        val loginUsername = findViewById<TextView>(R.id.login_username)
        val loginPassword = findViewById<TextView>(R.id.login_password)

        logBtn.setOnClickListener {
            val usernamelogin:String=loginUsername.text.toString().trim()
            val passwordlogin:String=loginPassword.text.toString().trim()

            if (usernamelogin.isNotEmpty()){
                database=FirebaseDatabase.getInstance().getReference("Users")
                database.child(usernamelogin).get().addOnSuccessListener {

                    if(it.exists()){
                        var username = it.child("username").value
                        var password = it.child("password").value

                        if(password==passwordlogin){
                            Toast.makeText(this,"Welcome to Brain Academy",Toast.LENGTH_LONG).show()
                            val intent =Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                            this.finish()


                        }else{
                            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,SignUp::class.java)
                        startActivity(intent)
                    }
                }
            }else{
                Toast.makeText(this,"Please enter the username",Toast.LENGTH_SHORT).show()
            }
        }




        val register_txt = findViewById<TextView>(R.id.register_txt)
        register_txt.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

}