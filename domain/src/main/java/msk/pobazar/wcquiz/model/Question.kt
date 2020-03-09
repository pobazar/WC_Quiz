package msk.pobazar.wcquiz.model

data class Question(
        val question: String,
        val answers: List<String>,
        val answerRight: String,
        val image: String
)