package msk.pobazar.wcquiz.domain.model

import android.graphics.Bitmap

data class GameResult(
    val question: String,
    val answer: String,
    val answerRight: String,
    val image: Bitmap
) {
    val isRight: Boolean
        get() = answer == answerRight
}