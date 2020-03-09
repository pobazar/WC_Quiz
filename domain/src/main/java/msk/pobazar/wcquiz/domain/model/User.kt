package msk.pobazar.wcquiz.domain.model

import msk.pobazar.wcquiz.domain.model.Score

data class User(
        val name: String,
        val email: String,
        val bestScore: Score
)