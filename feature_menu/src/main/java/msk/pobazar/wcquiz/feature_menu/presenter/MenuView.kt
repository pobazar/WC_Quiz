package msk.pobazar.wcquiz.feature_menu.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MenuView: MvpView {
}