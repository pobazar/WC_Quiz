package msk.pobazar.wcquiz.feature_game.viewData

import android.net.Uri

data class GameViewData(
    val question: String,
    val answers: List<String>,
    val answerRight: String,
    val imageUrl: String
)