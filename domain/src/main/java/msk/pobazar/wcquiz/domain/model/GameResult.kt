package msk.pobazar.wcquiz.domain.model

import android.net.Uri

data class GameResult(
    val question: String,
    val answer: String,
    val answerRight: String,
    val image: String
) {
    val isRight: Boolean
        get() = answer == answerRight
}