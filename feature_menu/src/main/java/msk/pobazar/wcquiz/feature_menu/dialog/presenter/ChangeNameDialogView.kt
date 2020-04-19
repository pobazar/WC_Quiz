package msk.pobazar.wcquiz.feature_menu.dialog.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface ChangeNameDialogView : MvpView {

    fun setName(name: String)

    fun setCountSymbols(count: String)

    fun closeDialog()
}