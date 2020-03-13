package msk.pobazar.wcquiz.navigation

import msk.pobazar.wcquiz.core.navigation.screens.NavigationDialog
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.navigation.screens.SupportAppDialog
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface ScreenFactory {
    fun createScreen(screen: NavigationScreen): SupportAppScreen
    fun createDialog(dialog: NavigationDialog): SupportAppDialog
}
