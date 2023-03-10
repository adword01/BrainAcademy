package com.example.brainacademy.model

data class QuizQuestion(
    var category : String ?= null, var subcategory : String ?= null, var question : String ?= null,var option1:String?=null,
    var option2:String?=null,var option3:String?=null,var option4:String?=null,var correctoption:String?=null
                        )
