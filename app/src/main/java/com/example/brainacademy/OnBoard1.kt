package com.example.brainacademy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.viewpager.widget.ViewPager
import com.example.brainacademy.adapter.OnBoardingViewPagerAdapter
import com.example.brainacademy.model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class OnBoard1 : AppCompatActivity() {

    var onBoardingViewPagerAdapter:OnBoardingViewPagerAdapter?=null
    var tabLayout: TabLayout?=null
    var onBoardingViewPager:ViewPager?=null
    var next:TextView?=null
    var position = 0
    var sharedPreferences: SharedPreferences?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        if(restorePrefData()){
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                // Network is connected, start the new activity
                val i=Intent(applicationContext,Login::class.java)
                startActivity(i)
                finish()
            } else {
                val i=Intent(applicationContext,NetworkActivity::class.java)
                startActivity(i)
                finish()
                // Network is not connected, show a message or perform some other action
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            }

        }

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        supportActionBar!!.hide()
        setContentView(R.layout.activity_on_board1)

        tabLayout=findViewById(R.id.tab_indicator)
        next=findViewById(R.id.next)

        val onBoardingData:MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Hi","Onboarding 1",R.drawable.on1))
        onBoardingData.add(OnBoardingData("Ad","Onboarding 2",R.drawable.on2))
        onBoardingData.add(OnBoardingData("Get Started","Onboarding 3",R.drawable.on3))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position=onBoardingViewPager!!.currentItem
        next?.setOnClickListener {

            if(position<onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem=position
            }
            if(position==onBoardingData.size){
                savePrefData()
                val i=Intent(applicationContext,SignUp::class.java)
                startActivity(i)
            }
        }
        tabLayout!!.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position=tab!!.position
                if(tab.position == onBoardingData.size-1){
                    next!!.text=="Get Started"
                }else{
                    next!!.text="Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPager=findViewById(R.id.screenViewPager)
        onBoardingViewPagerAdapter=OnBoardingViewPagerAdapter(this,onBoardingData)
        onBoardingViewPager!!.adapter=onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)

    }
    private fun savePrefData(){
        sharedPreferences=applicationContext.getSharedPreferences("pref",Context.MODE_PRIVATE)
        val editor=sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun",true)
        editor.apply()
    }

    private fun restorePrefData():Boolean{
        sharedPreferences=applicationContext.getSharedPreferences("pref",Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun",false)
    }

}