package com.example.brainacademy

import android.animation.Animator
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import androidx.core.content.ContentProviderCompat.requireContext
import com.airbnb.lottie.LottieAnimationView

class QuizVidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_vid)

//        val videoView = findViewById<VideoView>(R.id.quiz_vid)
//        var videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.start_quiz)
//        videoView.setVideoURI(videoUri)
//        videoView.start()

//        videoView.setOnCompletionListener{

        val lottie = findViewById<LottieAnimationView>(R.id.quiz_vid)

        lottie.addAnimatorUpdateListener {
            val intent = Intent(this,QuizActivity::class.java)
            startActivity(intent)
        }


//        }



    }

}