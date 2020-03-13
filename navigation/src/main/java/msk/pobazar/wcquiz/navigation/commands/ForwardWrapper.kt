package msk.pobazar.wcquiz.navigation.commands

import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

data class ForwardWrapper(
    val navigationScreen: NavigationScreen,
    val forward: Forward
) : Command
