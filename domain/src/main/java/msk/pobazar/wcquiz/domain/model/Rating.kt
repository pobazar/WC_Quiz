package msk.pobazar.wcquiz.domain.model

import java.util.Date

data class Rating(
    val countRight: Int,
    val score: Float,
    val time: Float,
    val user: String,
    val date: Date
)