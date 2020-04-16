package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.model.Theme
import toothpick.InjectConstructor

@InjectConstructor
class QuestionApiMapper {

    fun toDomain(api: List<QuestionResponse>, urls: Map<String, String>): List<Question> {
        api.map {
            it.picture = urls[it.picture] ?: ""
        }

        val list = mutableListOf<Question>()
        for (index in api.indices) {
            list.add(
                toDomain(
                    api = api[index]
                )
            )
        }
        return list
    }

    private fun toDomain(api: QuestionResponse): Question =
        Question(
            question = api.question,
            answers = listOf(
                api.answer1,
                api.answer2,
                api.answer3,
                api.answer4
            ),
            answerRight = api.answerR,
            imageUrl = api.picture,
            theme = themeFromCode(api.theme)
        )

    private fun themeFromCode(code: Int): Theme =
        when (code) {
            Theme.LOR.code -> Theme.LOR
            Theme.RAID.code -> Theme.RAID
            Theme.PERSON.code -> Theme.PERSON
            Theme.AREA.code -> Theme.AREA
            else -> Theme.LOR
        }
}