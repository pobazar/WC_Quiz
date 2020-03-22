package msk.pobazar.wcquiz.data_local.repo

import msk.pobazar.wcquiz.data_local.storage.LocalStorage
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import javax.inject.Inject

class QuestionRepoLocalImpl @Inject constructor(
    private val storage: LocalStorage
) : QuestionRepoLocal {
    override fun getRandomQuestions(count: Int): List<Question> {
        return storage.read<List<Question>>(
                tableName = QUESTION_BOOK,
                key = QUESTIONS
            )
            .shuffled()
            .take(count)
    }

    override fun setAllQuestions(data: List<Question>) {
        storage.write(
            tableName = QUESTION_BOOK,
            key = QUESTIONS,
            data = data
        )
    }

    override fun removeAllQuestions() {
        storage.erase(QUESTION_BOOK)
    }

    companion object {
        private const val QUESTION_BOOK = "question book"
        private const val QUESTIONS = "questions page"
    }
}