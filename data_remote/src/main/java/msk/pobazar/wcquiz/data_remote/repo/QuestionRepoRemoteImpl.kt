package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Single
import msk.pobazar.wcquiz.data_remote.R
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.domain.repo.remote.QuestionRepoRemote
import javax.inject.Inject

class QuestionRepoRemoteImpl @Inject constructor(
    private val resourceManager: ResourceManager
) : QuestionRepoRemote {
    override fun getAllQuestions(): Single<List<Question>> {
        return Single.just(
            listOf(
                Question("Вопрос 1", listOf("от1", "от2", "от3", "от4"), "от1", resourceManager.getBitmap(R.drawable.enot)),
                Question("Вопрос 2", listOf("от1", "от2", "от3", "от4"), "от2", resourceManager.getBitmap(R.drawable.enot))
            )
        )
    }
}