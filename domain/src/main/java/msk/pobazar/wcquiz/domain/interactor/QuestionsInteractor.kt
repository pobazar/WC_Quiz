package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.local.QuestionsRepoLocal
import javax.inject.Inject

class QuestionsInteractor @Inject constructor(
    private val questionsRepoLocal: QuestionsRepoLocal
) {

    fun getRandomLocal(count: Int): List<Question> {
        return questionsRepoLocal
            .getRandomQuestions(count)
    }
    
    fun getAllRemote(): Single<List<Question>>{
    
    }
}