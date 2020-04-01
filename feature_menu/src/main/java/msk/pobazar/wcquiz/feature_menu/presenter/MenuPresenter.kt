package msk.pobazar.wcquiz.feature_menu.presenter

import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import javax.inject.Inject

@InjectViewState
class MenuPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MenuView>() {
    fun onQuickGameClick() {
        router.show(NavigationScreen.Game(GameParams()))
    }

    fun onModsClick() {
        router.show(NavigationScreen.Mods)
    }

    fun onRatingClick() {
        router.show(NavigationScreen.Rating)
    }
}