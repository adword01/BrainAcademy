package com.example.brainacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.brainacademy.databinding.ActivityHomeBinding
import com.example.brainacademy.databinding.ActivityLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.SnapshotHolder

class HomeActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityHomeBinding

    lateinit var bottomNav : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())

        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


            bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        loadFragment(HomeFragment())
                        true
                    }
                    R.id.Search -> {
                        loadFragment(SearchFragment())
                        true
                    }
                    R.id.leaderboard -> {
                        loadFragment(LeaderboardFragment())
                        true
                    }
                    R.id.profile -> {
                        loadFragment(ProfileFragment())
                        true
                    }
                    R.id.NewQues -> {
                        val intent= Intent(this,NewQuestion::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> {
                        loadFragment(HomeFragment())
                        true
                    }
                }
            }

//                val username = it.child("username").value
//
//        binding.user.setText("Welcome"+name).toString()

    }

    private fun loadFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}