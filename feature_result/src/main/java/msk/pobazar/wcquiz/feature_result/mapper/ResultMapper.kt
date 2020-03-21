package msk.pobazar.wcquiz.feature_result.mapper

import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.feature_result.R
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewItem
import javax.inject.Inject

class ResultMapper @Inject constructor(
    private val resourceManager: ResourceManager
) {
    fun mapToResultViewData(
        gameResults: List<GameResult>,
        isShowAnswer: Boolean
    ) =
        ResultViewData(
            title = resourceManager.getString(
                R.string.count_right,
                gameResults.count { it.isRight }
            ),
            items = gameResults.map {
                ResultViewItem(
                    question = it.question,
                    answerRight = it.answerRight,
                    image = it.image,
                    color = resourceManager.getColor(
                        if (it.isRight)
                            R.color.right_answer
                        else
                            R.color.wrong_answer
                    ),
                    showAnswer = isShowAnswer
                )
            }
        )
}
