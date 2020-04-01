package msk.pobazar.wcquiz.feature_mods.presenter

import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import msk.pobazar.wcquiz.domain.model.Theme
import javax.inject.Inject

@InjectViewState
class ModsPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<ModsView>() {

    fun onGameClick(theme: Theme) {
        router.replace(NavigationScreen.Game(GameParams(theme)))
    }
}