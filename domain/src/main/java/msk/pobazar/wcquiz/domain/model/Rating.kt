package msk.pobazar.wcquiz.domain.model

import java.util.Date

data class Rating(
    val countRight: Int,
    val countAll: Int,
    val score: Float,
    val time: Long,
    val name: String,
    val date: Date
)