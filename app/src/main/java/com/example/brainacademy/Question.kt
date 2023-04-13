package com.example.brainacademy

data class Question(
    val question: String,
    val options: List<String>,
    var answer: Int = 0
)