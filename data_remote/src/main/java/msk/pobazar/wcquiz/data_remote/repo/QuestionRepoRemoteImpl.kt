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
                questions
            )
            .delay(3, TimeUnit.SECONDS)
    }

    private val questions = listOf(
        Question("Вопрос 1", listOf("от1", "от2", "от3", "от4"), "от1", image1),
        Question("Вопрос 2", listOf("от1", "от2", "от3", "от4"), "от1", image2),
        Question("Вопрос 3", listOf("от1", "от2", "от3", "от4"), "от1", image3),
        Question("Вопрос 4", listOf("от1", "от2", "от3", "от4"), "от1", image4),
        Question("Вопрос 5", listOf("от1", "от2", "от3", "от4"), "от1", image5)
    )

    companion object {
        private const val image1 = "https://avatars.mds.yandex.net/get-pdb/33827/f66978a3-8ec7-464a-a7bd-74a5fcc05a07/s375"
        private const val image2 = "https://avatars.mds.yandex.net/get-pdb/202366/5b6d73f0-bd5c-4b37-ae7e-7a071b1e7e82/s1200"
        private const val image3 = "https://avatars.mds.yandex.net/get-pdb/2474517/44124481-ac69-445d-84ea-3cc238048083/s1200"
        private const val image4 = "https://avatars.mds.yandex.net/get-pdb/477388/77f64197-87d2-42cf-9305-14f49c65f1da/s375"
        private const val image5 = "https://avatars.mds.yandex.net/get-pdb/28866/e97c1a47-7e4c-4578-8305-6cabcb524520/s375"
    }
}