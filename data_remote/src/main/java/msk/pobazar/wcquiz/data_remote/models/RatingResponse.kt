package msk.pobazar.wcquiz.data_remote.models

data class RatingResponse(
    val countAll: Int? = 0,
    val countRight: Int? = 0,
    val score: Float? = 0F,
    val time: Float? = 0F,
    val user: String? = "",
    val winStrick: Int? = 0
)