package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Question

interface QuestionRepoRemote {

    fun getAllQuestions(): Single<List<Question>>
}