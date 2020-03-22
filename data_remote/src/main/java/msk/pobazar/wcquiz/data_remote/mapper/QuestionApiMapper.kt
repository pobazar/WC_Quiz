package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.model.Question
import toothpick.InjectConstructor

@InjectConstructor
class QuestionApiMapper {

    fun mapApiToQuestion(api: Pair<List<String>, List<QuestionResponse>>): List<Question> {
        val list = mutableListOf<Question>()
        for (i in if (api.first.size > api.second.size) api.second.indices else api.first.indices) {
            list.add(
                mapApiToQuestion(api.first[i], api.second[i])
            )
        }
        return list
    }

    private fun mapApiToQuestion(image: String, api: QuestionResponse) =
        Question(
            question = api.question.orEmpty(),
            answers = listOf(
                api.answer1.orEmpty(),
                api.answer2.orEmpty(),
                api.answer3.orEmpty(),
                api.answer4.orEmpty()
            ),
            answerRight = api.answerR.orEmpty(),
            imageUrl = image
        )
}