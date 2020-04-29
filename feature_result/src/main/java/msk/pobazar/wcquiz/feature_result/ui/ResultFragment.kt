package msk.pobazar.wcquiz.feature_result.ui

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_result.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.core.base.ui.BaseFragment
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.feature_result.R
import msk.pobazar.wcquiz.feature_result.presenter.ResultPresenter
import msk.pobazar.wcquiz.feature_result.presenter.ResultView
import msk.pobazar.wcquiz.feature_result.ui.adapter.ResultListAdapter
import javax.inject.Inject

class ResultFragment : BaseFragment(), ResultView {
    override val layout: Int = R.layout.fragment_result

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: ResultPresenter

    private val adapter = ResultListAdapter()

    override fun initUi() {
        rvResult.adapter = adapter
        rvResult.layoutManager = LinearLayoutManager(context)
    }

    override fun initUx() {
        btnResultPlayAgain.setOnClick { presenter.onAgainPlayClick() }
        btnResultShowAnswer.setOnClick { presenter.onShowAnswers() }
    }

    override fun setResults(results: List<ViewItem>) {
        adapter.replace(results)
    }

    override fun setCountRight(score: String) {
        tvResultCountRight.text = score
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}