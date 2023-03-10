package com.example.brainacademy.repository

import androidx.lifecycle.MutableLiveData
import com.example.brainacademy.Questions
import com.example.brainacademy.User
import com.google.firebase.database.*

class SearchRepository {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Question Database")

    @Volatile private var INSTANCE : SearchRepository ?= null

    fun getInstance() : SearchRepository{
        return INSTANCE ?: synchronized(this){

            val instance = SearchRepository()
            INSTANCE = instance
            instance
        }
    }


    fun loadQuestion(questionList : MutableLiveData<List<Questions>>){

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val qList : List<Questions> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(Questions::class.java)!!

                    }

                    questionList.postValue(qList)

                }catch (e : Exception){


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }
}