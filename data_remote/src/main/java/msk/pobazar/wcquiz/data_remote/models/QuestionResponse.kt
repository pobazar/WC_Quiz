package msk.pobazar.wcquiz.data_remote.models

data class QuestionResponse(
    val answer1: String? = "",
    val answer2: String? = "",
    val answer3: String? = "",
    val answer4: String? = "",
    val answerR: String? = "",
    val picture: String? = "",
    val question: String? = ""
)