package msk.pobazar.wcquiz.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import msk.pobazar.wcquiz.core.navigation.screens.NavigationDialog
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.feature_game.ui.GameFragment
import msk.pobazar.wcquiz.feature_menu.dialog.ui.ChangeNameDialogFragment
import msk.pobazar.wcquiz.feature_menu.ui.MenuFragment
import msk.pobazar.wcquiz.feature_menu.ui.SplashFragment
import msk.pobazar.wcquiz.feature_mods.ui.ModsFragment
import msk.pobazar.wcquiz.feature_rating.ui.RatingFragment
import msk.pobazar.wcquiz.feature_result.ui.ResultFragment
import msk.pobazar.wcquiz.navigation.screens.SupportAppDialog
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class ScreenFactoryImpl @Inject constructor(
    private val context: Context
) : ScreenFactory {

    override fun createScreen(screen: NavigationScreen): SupportAppScreen {
        val screenKey = screen::class.java.canonicalName
        return when (screen) {
            is NavigationScreen.Splash -> fragment(screenKey) {
                SplashFragment.newInstance()
            }
            is NavigationScreen.MainMenu -> fragment(screenKey) {
                MenuFragment.newInstance()
            }
            is NavigationScreen.Game -> fragment(screenKey) {
                GameFragment.newInstance(screen.params)
            }
            is NavigationScreen.Result -> fragment(screenKey) {
                ResultFragment.newInstance()
            }
            is NavigationScreen.Rating -> fragment(screenKey) {
                RatingFragment.newInstance()
            }
            is NavigationScreen.Mods -> fragment(screenKey) {
                ModsFragment.newInstance()
            }
        }
    }

    override fun createDialog(dialog: NavigationDialog): SupportAppDialog {
        return when (dialog) {
            is NavigationDialog.ChangeName -> dialog {
                ChangeNameDialogFragment.newInstance()
            }
        }
    }

    private fun fragment(
        screenKey: String?,
        createFragment: () -> Fragment
    ): SupportAppScreen = object : SupportAppScreen() {
        override fun getFragment(): Fragment = createFragment()
        override fun getScreenKey(): String? = screenKey
    }

    private fun activity(createIntent: (context: Context) -> Intent): SupportAppScreen =
        object : SupportAppScreen() {
            override fun getActivityIntent(context: Context): Intent = createIntent(context)
        }

    private fun dialog(
        createDialog: () -> DialogFragment
    ): SupportAppDialog = object : SupportAppDialog() {
        override fun getDialog(): DialogFragment = createDialog()
    }
}
