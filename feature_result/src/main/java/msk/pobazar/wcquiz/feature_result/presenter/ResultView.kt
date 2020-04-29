package msk.pobazar.wcquiz.feature_result.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import msk.pobazar.wcquiz.core.base.adapter.ViewItem

@StateStrategyType(AddToEndStrategy::class)
interface ResultView : MvpView {

    fun setResults(results: List<ViewItem>)

    fun setCountRight(score: String)
}