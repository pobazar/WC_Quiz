package msk.pobazar.wcquiz.feature_game.mapper

import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.feature_game.viewData.GameViewData
import javax.inject.Inject

class GameMapper @Inject constructor(

) {

    fun mapToGameViewData(question: Question): GameViewData =
        GameViewData(
            question = question.question,
            answers = question.answers,
            answerRight = question.answerRight,
            image = null
        )

}