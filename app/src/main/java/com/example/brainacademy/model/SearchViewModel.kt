package com.example.brainacademy.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brainacademy.Questions
import com.example.brainacademy.User
import com.example.brainacademy.repository.SearchRepository

class SearchViewModel: ViewModel()  {


        private val repository : SearchRepository
        private val allQuestion = MutableLiveData<List<Questions>>()
        val allQuestions : LiveData<List<Questions>> = allQuestion


        init {
            repository = SearchRepository().getInstance()
            repository.loadQuestion(allQuestion)
        }
}