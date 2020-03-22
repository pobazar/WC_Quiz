package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse

interface QuestionApi {

    fun readAllQuestions(): PublishSubject<List<QuestionResponse>>

    fun writeAll(data: List<QuestionResponse>)
}