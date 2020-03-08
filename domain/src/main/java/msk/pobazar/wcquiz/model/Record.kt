package com.example.domain.model

import java.util.Date

data class Record(
        val user: User,
        val score: Score,
        val date: Date
)