package msk.pobazar.wcquiz.feature_menu.view

import kotlinx.android.synthetic.main.fragment_menu.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.feature_menu.presenter.MenuPresenter
import javax.inject.Inject

class MenuFragment : BaseFragment() {

    override val layout: Int = msk.pobazar.wcquiz.feature_menu.R.layout.fragment_menu

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: MenuPresenter

    override fun initUi() {
    }

    override fun initUx() {
        btnMenuQuickPlay.setOnClick { presenter.onQuickGameClick() }
        btnMenuMods.setOnClick { presenter.onModeClick() }
        btnMenuRating.setOnClick { presenter.onRatingClick() }
    }
}