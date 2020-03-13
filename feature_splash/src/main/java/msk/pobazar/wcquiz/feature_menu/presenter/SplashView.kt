package msk.pobazar.wcquiz.feature_menu.presenter

import moxy.MvpView

interface SplashView: MvpView {
    fun showProgress(isShow: Boolean)

    fun showError(isShow: Boolean)
}