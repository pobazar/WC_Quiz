package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.domain.model.Question
import toothpick.InjectConstructor

@InjectConstructor
class QuestionApiMapper {

    fun mapApiToQuestion(api: msk.pobazar.wcquiz.data_remote.models.QuestionResponse) =
        Question(
            question = api.question.orEmpty(),
            answers = listOf(
                api.answer1.orEmpty(),
                api.answer2.orEmpty(),
                api.answer3.orEmpty(),
                api.answer4.orEmpty()
            ),
            answerRight = api.answerR.orEmpty(),
            imageUrl = api.picture.orEmpty()
        )
}