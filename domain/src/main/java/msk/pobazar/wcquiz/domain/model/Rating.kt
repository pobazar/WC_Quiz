package msk.pobazar.wcquiz.domain.model

data class Rating(
    val countRight: Int,
    val score: Float,
    val time: Float,
    val user: String
)