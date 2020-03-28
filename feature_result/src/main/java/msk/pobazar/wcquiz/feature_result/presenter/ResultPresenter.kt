package msk.pobazar.wcquiz.feature_result.presenter

import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.domain.interactor.RatingInteractor
import msk.pobazar.wcquiz.domain.interactor.ResultInteractor
import msk.pobazar.wcquiz.feature_result.mapper.ResultMapper
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import java.util.Date
import java.util.Random
import javax.inject.Inject

class ResultPresenter @Inject constructor(
    private val router: Router,
    private val resultInteractor: ResultInteractor,
    private val ratingInteractor: RatingInteractor,
    private val resultMapper: ResultMapper
) : BasePresenter<ResultView>() {

    private lateinit var resultViewData: ResultViewData
    override fun attachView(view: ResultView?) {
        super.attachView(view)
        resultInteractor.isShowAnswer = false
        setupResult()
        sendRating()
    }

    fun onAgainPlayClick() {
        router.replace(NavigationScreen.Game)
    }

    fun onShowAnswers() {
        resultInteractor.isShowAnswer = true
        setupResult()
    }

    private fun setupResult() {
        resultViewData = resultMapper.mapToResultViewData(
            gameResults = resultInteractor.getResult(),
            isShowAnswer = resultInteractor.isShowAnswer
        )

        viewState.setResults(resultViewData.items)
        viewState.setCountRight(resultViewData.title)
    }

    private fun sendRating() {
        val result = resultInteractor.getResult()
        ratingInteractor.setNew(
            //TODO  Имя игрока
            user = Random().nextInt(100).toString(),
            countRight = result.countRight,
            countAll = result.results.size,
            time = result.time,
            winStrick = result.winStrick,
            date = Date()
        )

    }
}