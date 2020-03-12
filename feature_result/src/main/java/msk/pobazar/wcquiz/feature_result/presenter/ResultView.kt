package msk.pobazar.wcquiz.feature_result.presenter

import moxy.MvpView
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData

interface ResultView: MvpView {

    fun setResults(results: List<ResultViewData>)

    fun setCountRight(countRight: String)
}