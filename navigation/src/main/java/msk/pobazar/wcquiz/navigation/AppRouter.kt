package msk.pobazar.wcquiz.navigation

import msk.pobazar.wcquiz.navigation.commands.ForwardWrapper
import msk.pobazar.wcquiz.navigation.commands.OpenDialog
import msk.pobazar.wcquiz.navigation.commands.Recreate
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.navigation.screens.SupportAppDialog
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward

class AppRouter : Router() {

    fun recreate(
        screen: SupportAppScreen
    ) {
        executeCommands(
            Recreate(
                screen = screen
            )
        )
    }

    fun openDialog(dialog: SupportAppDialog) {
        executeCommands(OpenDialog(dialog))
    }

    fun navigateTo(navigationScreen: NavigationScreen, screen: SupportAppScreen) {
        executeCommands(ForwardWrapper(navigationScreen, Forward(screen)))
    }
}
