package msk.pobazar.wcquiz.feature_game.viewData

import android.graphics.Bitmap

data class GameViewData(
    val question: String,
    val answers: List<String>,
    val answerRight: String,
    val image: Bitmap
)