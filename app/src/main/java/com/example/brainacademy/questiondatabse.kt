package com.example.brainacademy
class questiondatabse(
    var description: String = "",
    var option1: String = "",
    var option2: String = "",
    var option3: String = "",
    var option4: String = "",
    var correctanswer: String = "",
    var useranswer: String = ""
) {

    fun getOptionAt(position: Int): String {
        return when (position) {
            0 -> option1
            1 -> option2
            2 -> option3
            3 -> option4
            else -> ""
        }
    }
}
