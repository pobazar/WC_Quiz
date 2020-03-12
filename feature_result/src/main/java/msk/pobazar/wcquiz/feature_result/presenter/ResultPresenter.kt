package msk.pobazar.wcquiz.feature_result.presenter

import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.transitionsParams.ResultParams
import msk.pobazar.wcquiz.feature_result.mapper.ResultMapper
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import javax.inject.Inject

class ResultPresenter @Inject constructor(
    private val resultMapper: ResultMapper,
    private val params: ResultParams
) : BasePresenter<ResultView>() {

    override fun attachView(view: ResultView?) {
        super.attachView(view)
        setResults(
            params.results
                .map(resultMapper::mapToResultViewData)
        )
    }

    fun onAgainPlayClick() {

    }

    fun onShowAnswers() {

    }

    private fun setResults(results: List<ResultViewData>) {
        viewState.setResults(results)
    }
}