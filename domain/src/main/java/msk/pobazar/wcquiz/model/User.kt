package msk.pobazar.wcquiz.model

import msk.pobazar.wcquiz.model.Score

data class User(
        val name: String,
        val email: String,
        val bestScore: Score
)