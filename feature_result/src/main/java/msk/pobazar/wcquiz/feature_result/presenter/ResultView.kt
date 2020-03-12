package msk.pobazar.wcquiz.feature_result.presenter

import moxy.MvpView
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem

interface ResultView : MvpView {

    fun setResults(results: List<ViewItem>)

    fun setCountRight(countRight: String)
}