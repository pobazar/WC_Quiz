package msk.pobazar.wcquiz.data_local.repo

import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.local.QuestionsRepoLocal
import toothpick.InjectConstructor

@InjectConstructor
class QuestionsRepoLocalImpl : QuestionsRepoLocal {
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
        Question("Вопрос 1", listOf("от1", "от2", "от3", "от4"), "от1",
            "https://yandex.ru/images/search?from=tabbar&text=gs%3A%2F%2Fwc-quiz.appspot.com%2Fimages%2Farcan_magia.jpg&pos=3&img_url=https%3A%2F%2Fimg1.akspic.ru%2Fimage%2F131941-banff-park-pustynya-nacionalnyj_park-gornyj_relef-3840x2160.jpg&rpt=simage"),
        Question("Вопрос 2", listOf("от1", "от2", "от3", "от4"), "от2",
            "https://yandex.ru/images/search?from=tabbar&text=gs%3A%2F%2Fwc-quiz.appspot.com%2Fimages%2Farcan_magia.jpg&pos=8&img_url=https%3A%2F%2Ff.vividscreen.info%2Fsoft%2F247e51daf1c684887d839bc68753a3bb%2FAbstract-Black-And-Red-Cubes-2560x1600.jpg&rpt=simage")
    )
}