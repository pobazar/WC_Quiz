package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.remote.QuestionsRepoRemote
import javax.inject.Inject

class QuestionsInteractor @Inject constructor(
    private val questionsRepoRemote: QuestionsRepoRemote
) {
    
    fun getRandom(count: Int): Single<List<Question>> {
        return questionsRepoRemote
            .getRandomQuestions(count)
            .observeOn(Schedulers.io())
    }
}