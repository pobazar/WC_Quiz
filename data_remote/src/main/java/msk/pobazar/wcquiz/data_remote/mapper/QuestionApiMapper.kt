package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.model.Question
import toothpick.InjectConstructor

@InjectConstructor
class QuestionApiMapper {

    fun mapApiToQuestion(api: List<QuestionResponse>, urls: List<String>): List<Question> {
        val list = mutableListOf<Question>()
        for (index in api.indices) {
            list.add(
                mapApiToQuestion(
                    api = api[index],
                    url = urls[index]
                )
            )
        }
        return list
    }

    private fun mapApiToQuestion(api: QuestionResponse, url: String): Question =
        Question(
            question = api.question.orEmpty(),
            answers = listOf(
                api.answer1.orEmpty(),
                api.answer2.orEmpty(),
                api.answer3.orEmpty(),
                api.answer4.orEmpty()
            ),
            answerRight = api.answerR.orEmpty(),
            imageUrl = url
        )
}