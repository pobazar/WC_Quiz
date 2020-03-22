package msk.pobazar.wcquiz.feature_game.mapper

import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.feature_game.viewData.GameViewData
import toothpick.InjectConstructor

@InjectConstructor
class GameMapper {

    fun mapToGameViewData(question: Question): GameViewData =
        GameViewData(
            question = question.question,
            answers = question.answers,
            answerRight = question.answerRight,
            imageUrl = question.imageUrl
        )

}