package msk.pobazar.wcquiz.navigation.local

import androidx.fragment.app.Fragment
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.navigators.FragmentNavigator
import msk.pobazar.wcquiz.navigation.AppNavigator
import msk.pobazar.wcquiz.navigation.RouterImpl
import msk.pobazar.wcquiz.navigation.ScreenFactory
import javax.inject.Inject

class LocalNavigatorImpl @Inject constructor(
    private val ciceroneHolder: LocalCiceroneHolder,
    private val screenFactory: ScreenFactory
) : FragmentNavigator {

    private var navigator: AppNavigator? = null
    private lateinit var fragment: Fragment
    private val screenTag: String
        get() = fragment::class.java.simpleName

    override val router: Router
        get() = RouterImpl(
            appRouter = ciceroneHolder.getRouter(screenTag),
            factory = screenFactory
        )

    override fun setup(fragment: Fragment, containerId: Int) {
        this.fragment = fragment
        if (navigator == null) {
            navigator = AppNavigator(
                fragmentActivity = fragment.requireActivity(),
                contentFrame = containerId,
                fragmentManager = fragment.childFragmentManager
            )
        }
    }

    override fun attach() {
        ciceroneHolder
            .getCicerone(screenTag)
            .navigatorHolder
            .setNavigator(navigator)
    }

    override fun detach() {
        ciceroneHolder
            .getCicerone(screenTag)
            .navigatorHolder
            .removeNavigator()
    }

    override fun destroy() {
        ciceroneHolder
            .releaseCicerone(screenTag)

        navigator = null
    }
}
