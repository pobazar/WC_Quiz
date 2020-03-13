package msk.pobazar.wcquiz.core.navigation.navigators

import androidx.fragment.app.Fragment
import msk.pobazar.wcquiz.core.navigation.Router

interface FragmentNavigator {
    val router: Router
    fun setup(fragment: Fragment, containerId: Int)
    fun attach()
    fun detach()
    fun destroy()
}
