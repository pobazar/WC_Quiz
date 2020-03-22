package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse

interface QuestionApi {

    fun readAllQuestions(): Observable<List<QuestionResponse>>

    fun writeAll(data: List<QuestionResponse>)
}