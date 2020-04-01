package msk.pobazar.wcquiz.feature_mods.ui

import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Constraints
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_mods.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.domain.model.Theme
import msk.pobazar.wcquiz.feature_mods.R
import msk.pobazar.wcquiz.feature_mods.presenter.ModsPresenter
import msk.pobazar.wcquiz.feature_mods.presenter.ModsView
import javax.inject.Inject

class ModsFragment : BaseFragment(), ModsView {

    override val layout: Int = R.layout.fragment_mods
    private var curIndex = 0

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: ModsPresenter
    override fun initUi() {
    }

    override fun initUx() {
    }

    override fun addMod(theme: Theme) {
        clModsContainer.addView(
            AppCompatTextView(context).apply {
                id = "11221$curIndex".toInt()
                text = theme.title
                gravity = Gravity.CENTER
                background = ContextCompat.getDrawable(context, R.drawable.mods_button)
                setTextAppearance(R.style.mods_button)
                setOnClickListener { presenter.onGameClick(theme) }
                layoutParams = Constraints.LayoutParams(R.dimen.button_width, R.dimen.button_height).apply {
                    topMargin = if (curIndex == 0) 360 else 24
                    topToBottom = if (curIndex == 0) R.id.parent else "11221${curIndex - 1}".toInt()
                    startToStart = R.id.parent
                    endToEnd = R.id.parent
                }
            }
        )
        curIndex++
    }

    companion object {
        fun newInstance() = ModsFragment()
    }
}