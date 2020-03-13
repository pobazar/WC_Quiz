package msk.pobazar.wcquiz.navigation

import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationDialog
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import javax.inject.Inject

class RouterImpl @Inject constructor(
    private val appRouter: AppRouter,
    private val factory: ScreenFactory
) : Router {

    override fun show(screen: NavigationScreen) {
        factory.createScreen(screen)
            .let { appRouter.navigateTo(screen, it) }
    }

    override fun replace(screen: NavigationScreen) {
        factory.createScreen(screen)
            .let(appRouter::replaceScreen)
    }

    override fun setRoot(screen: NavigationScreen) {
        factory.createScreen(screen)
            .let(appRouter::newRootScreen)
    }

    override fun exit() {
        appRouter.exit()
    }

    override fun backTo(screen: NavigationScreen) {
        factory.createScreen(screen)
            .let(appRouter::backTo)
    }

    override fun recreate(screen: NavigationScreen) {
        factory.createScreen(screen)
            .let(appRouter::recreate)
    }

    override fun openDialog(dialog: NavigationDialog) {
        factory.createDialog(dialog)
            .let(appRouter::openDialog)
    }
}
