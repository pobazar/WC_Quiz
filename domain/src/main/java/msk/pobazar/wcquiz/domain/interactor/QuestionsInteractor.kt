package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Observable
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.model.Theme
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import msk.pobazar.wcquiz.domain.repo.remote.QuestionRepoRemote
import javax.inject.Inject

class QuestionsInteractor @Inject constructor(
    private val questionRepoLocal: QuestionRepoLocal,
    private val questionRepoRemote: QuestionRepoRemote
) {

    fun getRandomLocal(count: Int): List<Question> =
        questionRepoLocal
            .getRandomQuestions(count)

    fun getRandomLocal(count: Int, theme: Theme): List<Question> =
        questionRepoLocal
            .getRandomQuestions(count, theme)

    fun getAllRemoteAndToLocal(): Observable<List<Question>> =
        questionRepoRemote
            .getAllQuestions()
            .doOnNext(questionRepoLocal::setAllQuestions)
}