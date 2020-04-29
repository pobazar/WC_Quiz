package msk.pobazar.wcquiz.feature_result.mapper

import msk.pobazar.wcquiz.domain.interactor.ScoreInteractor
import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.feature_result.R
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewItem
import javax.inject.Inject

class ResultMapper @Inject constructor(
    private val scoreInteractor: ScoreInteractor,
    private val resourceManager: ResourceManager
) {
    fun toViewData(
        gameResults: GameResult,
        isShowAnswer: Boolean
    ) =
        ResultViewData(
            title = resourceManager.getString(
                R.string.score_result,
                scoreInteractor.calculate(gameResults).toLong().toString()
            ),
            items = gameResults.results.map {
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
