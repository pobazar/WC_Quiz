package msk.pobazar.wcquiz.feature_rating.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.view_error.ErrorType

@StateStrategyType(AddToEndStrategy::class)
interface RatingView : MvpView {

    fun setResults(results: List<ViewItem>)

    fun showError(type: ErrorType)

    fun showProgressSwipeRefresh(isShow: Boolean)

    fun showToolbarTitle(isShow: Boolean)
}