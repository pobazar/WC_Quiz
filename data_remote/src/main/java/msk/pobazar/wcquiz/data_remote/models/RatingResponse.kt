package msk.pobazar.wcquiz.data_remote.models

import java.util.Date

data class RatingResponse(
    val countAll: Int? = 0,
    val countRight: Int? = 0,
    val score: Float? = 0F,
    val time: Long? = 0L,
    val name: String? = "",
    val winStrick: Int? = 0,
    val date: Date? = Date(0)
)