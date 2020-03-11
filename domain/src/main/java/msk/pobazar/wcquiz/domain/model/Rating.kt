package msk.pobazar.wcquiz.domain.model

import java.util.Date

data class Rating(
        val user: User,
        val score: Score,
        val date: Date
)