package msk.pobazar.wcquiz.core.navigation

import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen

interface NavigatingFragment {
    fun navigateTo(screen: NavigationScreen): Boolean
}
