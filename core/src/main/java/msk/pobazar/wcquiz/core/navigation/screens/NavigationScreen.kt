package msk.pobazar.wcquiz.core.navigation.screens

import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import java.io.Serializable

sealed class NavigationScreen : Serializable {
    object Splash : NavigationScreen()
    object MainMenu : NavigationScreen()
    class Game(val params: GameParams) : NavigationScreen()
    object Result : NavigationScreen()
    object Rating : NavigationScreen()
    object Mods : NavigationScreen()
}
