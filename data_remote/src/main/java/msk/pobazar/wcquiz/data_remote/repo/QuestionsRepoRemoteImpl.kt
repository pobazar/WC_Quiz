package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.remote.QuestionsRepoRemote
import toothpick.InjectConstructor

@InjectConstructor
class QuestionsRepoRemoteImpl : QuestionsRepoRemote {
    override fun getAllQuestions(): Single<List<Question>> {
        return Single.just(
            listOf(
                Question("Вопрос 1", listOf("от1", "от2", "от3", "от4"), "от1",
                    "https://yandex.ru/images/search?from=tabbar&text=gs%3A%2F%2Fwc-quiz.appspot.com%2Fimages%2Farcan_magia.jpg&pos=3&img_url=https%3A%2F%2Fimg1.akspic.ru%2Fimage%2F131941-banff-park-pustynya-nacionalnyj_park-gornyj_relef-3840x2160.jpg&rpt=simage"),
                Question("Вопрос 2", listOf("от1", "от2", "от3", "от4"), "от2",
                    "https://yandex.ru/images/search?from=tabbar&text=gs%3A%2F%2Fwc-quiz.appspot.com%2Fimages%2Farcan_magia.jpg&pos=8&img_url=https%3A%2F%2Ff.vividscreen.info%2Fsoft%2F247e51daf1c684887d839bc68753a3bb%2FAbstract-Black-And-Red-Cubes-2560x1600.jpg&rpt=simage")
            )
        )
    }
}