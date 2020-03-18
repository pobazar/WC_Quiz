package msk.pobazar.wcquiz.feature_menu.ui

import kotlinx.android.synthetic.main.fragment_splash.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.core.extensions.visible
import msk.pobazar.wcquiz.feature_menu.presenter.SplashPresenter
import msk.pobazar.wcquiz.feature_menu.presenter.SplashView
import msk.pobazar.wcquiz.feature_splash.R
import javax.inject.Inject

class SplashFragment : BaseFragment(), SplashView {

    override val layout: Int = R.layout.fragment_splash

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: SplashPresenter

    override fun initUi() {

    }

    override fun initUx() {
        btnSplahRetry.setOnClick { presenter.onRetryClick() }
    }

    override fun showProgress(isShow: Boolean) {
        pbSplash.visible(isShow)
    }

    override fun showError(isShow: Boolean) {
        tvSplashErrorDesc.visible(isShow)
        btnSplahRetry.visible(isShow)
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}