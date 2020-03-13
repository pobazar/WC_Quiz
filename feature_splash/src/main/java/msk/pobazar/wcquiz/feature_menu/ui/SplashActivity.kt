package msk.pobazar.wcquiz.feature_menu.ui

import android.os.Bundle
import msk.pobazar.wcquiz.core.base.BaseActivity
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.setRoot(NavigationScreen.Splash)
        }
    }
}