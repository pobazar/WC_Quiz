package msk.pobazar.wcquiz.navigation.local

import msk.pobazar.wcquiz.navigation.AppRouter
import javax.inject.Inject
import ru.terrakok.cicerone.Cicerone

class LocalCiceroneHolder @Inject constructor() {

    private val containers: MutableMap<String, Cicerone<AppRouter>> = mutableMapOf()

    fun getCicerone(containerTag: String): Cicerone<AppRouter> {
        return containers.getOrPut(containerTag) {
            Cicerone.create(AppRouter())
        }
    }

    fun releaseCicerone(containerTag: String) {
        containers.remove(containerTag)
    }

    fun getRouter(containerTag: String): AppRouter =
        getCicerone(containerTag).router
}
