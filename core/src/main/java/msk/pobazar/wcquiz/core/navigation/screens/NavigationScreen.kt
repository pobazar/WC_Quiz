package msk.pobazar.wcquiz.core.navigation.screens

import java.io.Serializable

sealed class NavigationScreen : Serializable {
    object Splash : NavigationScreen()
    object MainMenu : NavigationScreen()
    object Game : NavigationScreen()
    object Result : NavigationScreen()
    object Rating : NavigationScreen()
    object Mods : NavigationScreen()
}
