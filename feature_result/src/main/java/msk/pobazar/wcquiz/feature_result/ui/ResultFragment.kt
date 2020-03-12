package msk.pobazar.wcquiz.feature_result.ui

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_result.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.BaseFragment
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.core.navigation.transitionsParams.ResultParams
import msk.pobazar.wcquiz.feature_result.R
import msk.pobazar.wcquiz.feature_result.presenter.ResultPresenter
import msk.pobazar.wcquiz.feature_result.presenter.ResultView
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import toothpick.config.Module
import javax.inject.Inject

class ResultFragment : BaseFragment(), ResultView {
    override val layout: Int = R.layout.fragment_result

    override val moduleProvider: (Module) -> Unit
        get() = {
            it.bind(ResultParams::class.java)
                .toInstance(arguments?.getParcelable(PARAMS))
        }

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: ResultPresenter

    override fun initUi() {
    }

    override fun initUx() {
        btnResultPlayAgain.setOnClick { presenter.onAgainPlayClick() }
        btnResultShowAnswer.setOnClick { presenter.onAgainPlayClick() }
    }

    override fun setResults(results: List<ResultViewData>) {

    }

    override fun setCountRight(countRight: String) {
        tvResultCountRight.text = countRight
    }

    companion object {
        private const val PARAMS = "result_fragment_params"

        fun newInstance(params: ResultParams) = ResultFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PARAMS, params)
            }
        }
    }
}