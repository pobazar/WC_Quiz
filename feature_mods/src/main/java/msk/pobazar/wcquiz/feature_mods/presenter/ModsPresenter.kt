package msk.pobazar.wcquiz.feature_mods.presenter

import moxy.InjectViewState
import msk.pobazar.wcquiz.core.analytics.AnalyticsKeys
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import msk.pobazar.wcquiz.domain.interactor.AnalyticsInteractor
import msk.pobazar.wcquiz.domain.model.Theme
import javax.inject.Inject

@InjectViewState
class ModsPresenter @Inject constructor(
    private val router: Router,
    private val analyticsInteractor: AnalyticsInteractor
) : BasePresenter<ModsView>() {

    fun onGameClick(theme: Theme) {
        analyticsInteractor.reportEvent(AnalyticsKeys.MODE, "{\"Mode\":\"${theme.title}\"}")
        router.replace(NavigationScreen.Game(GameParams(theme)))
    }
}