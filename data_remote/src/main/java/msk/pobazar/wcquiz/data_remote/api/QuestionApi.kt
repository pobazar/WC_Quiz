package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse

interface QuestionApi {

    fun readAll(): Observable<List<QuestionResponse>>
}