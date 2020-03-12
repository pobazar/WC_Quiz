package msk.pobazar.wcquiz.feature_result.mapper

import msk.pobazar.wcquiz.core.navigation.transitionsParams.ResultParams
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import toothpick.InjectConstructor

@InjectConstructor
class ResultMapper {
    fun mapToResultViewData(resultParams: ResultParams.ResultItem) =
        ResultViewData(
            question = resultParams.question,
            answerRight = resultParams.answerRight,
            image = resultParams.image,
            isRight = resultParams.answerRight == resultParams.answer
        )

}
