package msk.pobazar.wcquiz.core.navigation.navigators

import androidx.fragment.app.FragmentActivity

interface ActivityNavigator {
    fun onResumeFragments(activity: FragmentActivity, containerId: Int)
    fun onPause()
}
