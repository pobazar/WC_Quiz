package msk.pobazar.wcquiz.core.navigation

import msk.pobazar.wcquiz.core.navigation.screens.NavigationDialog
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen

interface Router {
    fun show(screen: NavigationScreen)
    fun replace(screen: NavigationScreen)
    fun setRoot(screen: NavigationScreen)
    fun exit()
    fun backTo(screen: NavigationScreen)
    fun recreate(screen: NavigationScreen)
    fun openDialog(dialog: NavigationDialog)
}
