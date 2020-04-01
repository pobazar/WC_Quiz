package msk.pobazar.wcquiz.feature_menu.ui

import kotlinx.android.synthetic.main.fragment_menu.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.feature_menu.R
import msk.pobazar.wcquiz.feature_menu.presenter.MenuPresenter
import msk.pobazar.wcquiz.feature_menu.presenter.MenuView
import javax.inject.Inject

class MenuFragment : BaseFragment(), MenuView {

    override val layout: Int = R.layout.fragment_menu

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: MenuPresenter

    override fun initUi() {
    }

    override fun initUx() {
        btnMenuQuickPlay.setOnClick { presenter.onQuickGameClick() }
        btnMenuMods.setOnClick { presenter.onModsClick() }
        btnMenuRating.setOnClick { presenter.onRatingClick() }
    }

    companion object {
        fun newInstance() = MenuFragment()
    }
}