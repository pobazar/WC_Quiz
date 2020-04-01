package msk.pobazar.wcquiz.feature_mods.ui

import kotlinx.android.synthetic.main.fragment_mods.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.domain.model.Theme
import msk.pobazar.wcquiz.feature_mods.R
import msk.pobazar.wcquiz.feature_mods.presenter.ModsPresenter
import msk.pobazar.wcquiz.feature_mods.presenter.ModsView
import javax.inject.Inject

class ModsFragment : BaseFragment(), ModsView {

    override val layout: Int = R.layout.fragment_mods

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: ModsPresenter

    override fun initUi() {
        btnMods1.text = Theme.LOR.title
        btnMods2.text = Theme.RAID.title
        btnMods3.text = Theme.PERSON.title
        btnMods4.text = Theme.AREA.title
    }

    override fun initUx() {
        btnMods1.setOnClick { presenter.onGameClick(Theme.LOR) }
        btnMods2.setOnClick { presenter.onGameClick(Theme.RAID) }
        btnMods3.setOnClick { presenter.onGameClick(Theme.PERSON) }
        btnMods4.setOnClick { presenter.onGameClick(Theme.AREA) }
    }

    companion object {
        fun newInstance() = ModsFragment()
    }
}