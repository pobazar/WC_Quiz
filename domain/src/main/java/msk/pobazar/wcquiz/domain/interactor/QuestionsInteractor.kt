package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.local.QuestionsRepoLocal
import javax.inject.Inject

class QuestionsInteractor @Inject constructor(
    private val questionsRepoLocal: QuestionsRepoLocal
) {

    fun getRandom(count: Int): List<Question> {
        return questionsRepoLocal
            .getRandomQuestions(count)
    }
}