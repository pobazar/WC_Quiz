package msk.pobazar.wcquiz.feature_menu.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import msk.pobazar.wcquiz.view_error.ErrorType

@StateStrategyType(AddToEndStrategy::class)
interface SplashView: MvpView {
    fun showError(type: ErrorType)

    fun showProgress(isShow: Boolean)
}