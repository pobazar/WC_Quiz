package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.domain.repo.remote.QuestionRepoRemote
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class QuestionRepoRemoteImpl @Inject constructor(
    private val resourceManager: ResourceManager
) : QuestionRepoRemote {
    override fun getAllQuestions(): Single<List<Question>> {
        return Single.just(
                listOf(
                    Question("Вопрос 1", listOf("от1", "от2", "от3", "от4"), "от1", image1),
                    Question("Вопрос 2", listOf("от1", "от2", "от3", "от4"), "от2", image2)
                )
            )
            .delay(3, TimeUnit.SECONDS)
    }

    companion object {
        private const val image1 = ""
        private const val image2 = ""
    }
}