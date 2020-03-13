package msk.pobazar.wcquiz.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import msk.pobazar.wcquiz.core.navigation.NavigatingFragment
import msk.pobazar.wcquiz.navigation.commands.ForwardWrapper
import msk.pobazar.wcquiz.navigation.commands.OpenDialog
import msk.pobazar.wcquiz.navigation.commands.Recreate
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class AppNavigator(
    private val fragmentActivity: FragmentActivity,
    private val contentFrame: Int,
    private val fragmentManager: FragmentManager = fragmentActivity.supportFragmentManager
) : SupportAppNavigator(fragmentActivity, fragmentManager, contentFrame) {

    override fun applyCommand(command: Command?) {
        when (command) {
            is ForwardWrapper -> {
                fragmentManager.fragments.forEach { fragment ->
                    if (fragment is NavigatingFragment && fragment.navigateTo(command.navigationScreen)) {
                        return
                    }
                }
                super.applyCommand(command.forward)
            }
            is Recreate -> {
                activityReplace(Replace(command.screen))
                fragmentActivity.overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            }

            is OpenDialog -> {
                command.dialog.getDialog().show(fragmentManager, "")
            }

            else -> super.applyCommand(command)
        }
    }

    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction?
    ) {
        when (command) {
            is Forward -> fragmentTransaction?.setCustomAnimations(
                R.anim.slide_from_right,
                R.anim.slide_to_left,
                R.anim.slide_from_left,
                R.anim.slide_to_right
            )
        }
    }
}
