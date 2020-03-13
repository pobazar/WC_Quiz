package msk.pobazar.wcquiz.navigation.commands

import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

data class Recreate(
    val screen: SupportAppScreen
) : Command
