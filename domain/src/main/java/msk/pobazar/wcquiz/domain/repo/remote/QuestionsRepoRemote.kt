package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Question

interface QuestionsRepoRemote {
    fun getRandomQuestions(count: Int): Single<List<Question>>
}