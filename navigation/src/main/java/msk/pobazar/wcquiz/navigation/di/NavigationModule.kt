package msk.pobazar.wcquiz.navigation.di


import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.navigation.AppRouter
import msk.pobazar.wcquiz.navigation.RouterImpl
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import toothpick.config.Module

class NavigationModule: Module() {

    init {
        val cicerone = Cicerone.create(AppRouter())

        bind(AppRouter::class.java)
            .toInstance(cicerone.router)

        bind(NavigatorHolder::class.java)
            .toInstance(cicerone.navigatorHolder)

        bind(Router::class.java)
            .to(RouterImpl::class.java)
            .singleton()
    }
}