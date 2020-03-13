package msk.pobazar.wcquiz.navigation.di

import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.navigators.ActivityNavigator
import msk.pobazar.wcquiz.core.navigation.navigators.FragmentNavigator
import msk.pobazar.wcquiz.navigation.ActivityNavigatorImpl
import msk.pobazar.wcquiz.navigation.AppRouter
import msk.pobazar.wcquiz.navigation.RouterImpl
import msk.pobazar.wcquiz.navigation.ScreenFactory
import msk.pobazar.wcquiz.navigation.local.LocalNavigatorImpl
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import toothpick.config.Module

class NavigationModule : Module() {

    init {
        val cicerone = Cicerone.create(AppRouter())

        bind(ActivityNavigator::class.java)
            .to(ActivityNavigatorImpl::class.java)
            .singleton()

        bind(FragmentNavigator::class.java)
            .to(LocalNavigatorImpl::class.java)

        bind(NavigatorHolder::class.java)
            .toInstance(cicerone.navigatorHolder)

        bind(AppRouter::class.java)
            .toInstance(cicerone.router)

        bind(Router::class.java)
            .to(RouterImpl::class.java)
            .singleton()
    }
}