package msk.pobazar.wcquiz.feature_mods.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import msk.pobazar.wcquiz.domain.model.Theme

@StateStrategyType(AddToEndStrategy::class)
interface ModsView : MvpView {

    fun addMod(theme: Theme)
}