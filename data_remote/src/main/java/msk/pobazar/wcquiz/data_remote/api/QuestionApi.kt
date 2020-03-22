package msk.pobazar.wcquiz.data_remote.api

import msk.pobazar.wcquiz.data_remote.models.QuestionResponse

interface QuestionApi {

    fun readAllQuestions()

    fun writeAll(data: List<QuestionResponse>)
}