package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import msk.pobazar.wcquiz.domain.repo.remote.QuestionRepoRemote
import javax.inject.Inject

class QuestionsInteractor @Inject constructor(
    private val questionRepoLocal: QuestionRepoLocal,
    private val questionRepoRemote: QuestionRepoRemote
) {

    fun getRandomLocal(count: Int): List<Question> {
        return questionRepoLocal
            .getRandomQuestions(count)
    }

    fun getAllRemote(): Single<List<Question>> {
        return questionRepoRemote
            .getAllQuestions()
            .doOnSuccess{
                questionRepoLocal.setAllQuestions(it)
            }
    }
}