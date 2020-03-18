package msk.pobazar.wcquiz.feature_game.viewData

data class GameViewData(
    val question: String,
    val answers: List<String>,
    val answerRight: String,
    val imageUrl: String
)