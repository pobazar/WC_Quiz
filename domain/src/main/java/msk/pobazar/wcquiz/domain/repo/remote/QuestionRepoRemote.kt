package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Observable
import msk.pobazar.wcquiz.domain.model.Question

interface QuestionRepoRemote {

    fun getAllQuestions(): Observable<List<Question>>
}