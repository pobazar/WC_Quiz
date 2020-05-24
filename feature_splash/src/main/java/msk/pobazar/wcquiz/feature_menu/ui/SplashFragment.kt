package msk.pobazar.wcquiz.feature_menu.ui

import kotlinx.android.synthetic.main.fragment_splash.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.ui.BaseFragment
import msk.pobazar.wcquiz.core.extensions.visible
import msk.pobazar.wcquiz.feature_menu.presenter.SplashPresenter
import msk.pobazar.wcquiz.feature_menu.presenter.SplashView
import msk.pobazar.wcquiz.feature_splash.R
import msk.pobazar.wcquiz.view_error.ErrorType
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
        errorSplash.onActionClick { presenter.onRetryClick() }
    }

    override fun showError(type: ErrorType) {
        errorSplash.type = type
        errorSplash.visible(type != ErrorType.NONE)
    }

    override fun showProgress(isShow: Boolean) {
        loaderSplash.visible(isShow)
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}