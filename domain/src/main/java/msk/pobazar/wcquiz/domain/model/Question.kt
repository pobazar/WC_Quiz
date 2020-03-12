package msk.pobazar.wcquiz.domain.model

import android.graphics.Bitmap

data class Question(
        val question: String,
        val answers: List<String>,
        val answerRight: String,
        val image: Bitmap
)