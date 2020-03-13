package msk.pobazar.wcquiz.navigation

import androidx.fragment.app.FragmentActivity
import msk.pobazar.wcquiz.core.navigation.navigators.ActivityNavigator
import javax.inject.Inject
import ru.terrakok.cicerone.NavigatorHolder

class ActivityNavigatorImpl @Inject constructor(
    private val navigatorHolder: NavigatorHolder
) : ActivityNavigator {

    private var appNavigator: AppNavigator? = null

    override fun onResumeFragments(activity: FragmentActivity, containerId: Int) {
        if (appNavigator == null) {
            appNavigator = AppNavigator(activity, containerId)
        }
        navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
    }
}
