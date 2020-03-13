package msk.pobazar.wcquiz.feature_result.ui

import kotlinx.android.synthetic.main.fragment_result.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.core.navigation.transitionsParams.ResultParams
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
    
    private val resultAdapter = ResultListAdapter()
    
    override fun initUi() {
        rvResult.adapter = resultAdapter
    }
    
    override fun initUx() {
        btnResultPlayAgain.setOnClick { presenter.onAgainPlayClick() }
        btnResultShowAnswer.setOnClick { presenter.onShowAnswers() }
    }
    
    override fun setResults(results: List<ViewItem>) {
        resultAdapter.add(results)
    }
    
    override fun setCountRight(countRight: String) {
        tvResultCountRight.text = countRight
    }
    
    companion object {
        fun newInstance() = ResultFragment()
    }
}