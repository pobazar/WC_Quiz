package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.extensions.orZero
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.model.Theme
import toothpick.InjectConstructor

@InjectConstructor
class QuestionApiMapper {

    fun toDomain(api: List<QuestionResponse>, urls: List<String>): List<Question> {
        val list = mutableListOf<Question>()
        for (index in api.indices) {
            list.add(
                toDomain(
                    api = api[index],
                    url = urls[index]
                )
            )
        }
        return list
    }

    private fun toDomain(api: QuestionResponse, url: String): Question =
        Question(
            question = api.question.orEmpty(),
            answers = listOf(
                api.answer1.orEmpty(),
                api.answer2.orEmpty(),
                api.answer3.orEmpty(),
                api.answer4.orEmpty()
            ),
            answerRight = api.answerR.orEmpty(),
            imageUrl = url,
            theme = themeFromCode(api.theme.orZero())
        )

    private fun themeFromCode(code: Int): Theme =
        when (code) {
            0 -> Theme.LOR
            1 -> Theme.RAID
            2 -> Theme.PERSON
            3 -> Theme.AREA
            else -> Theme.LOR
        }
}