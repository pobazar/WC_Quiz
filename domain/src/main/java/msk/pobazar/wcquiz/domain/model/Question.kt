package msk.pobazar.wcquiz.domain.model

import android.net.Uri

data class Question(
        val question: String,
        val answers: List<String>,
        val answerRight: String,
        val imageUrl: String
)