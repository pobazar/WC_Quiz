package msk.pobazar.wcquiz.data_local.repo

import msk.pobazar.wcquiz.data_local.R
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import javax.inject.Inject

class QuestionRepoLocalImpl @Inject constructor(
    resourceManager: ResourceManager
) : QuestionRepoLocal {
    override fun getRandomQuestions(count: Int): List<Question> {
        return questions.take(count)
    }

    override fun setAllQuestions(questions: List<Question>) {
        TODO("Not yet implemented")
    }

    override fun removeAllQuestions() {
        TODO("Not yet implemented")
    }

    private val questions = listOf(
        Question("Вопрос 1", listOf("от1", "от2", "от3", "от4"), "от1", resourceManager.getBitmap(R.drawable.enot)),
        Question("Вопрос 2", listOf("от1", "от2", "от3", "от4"), "от2", resourceManager.getBitmap(R.drawable.enot))
    )
}