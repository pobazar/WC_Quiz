package msk.pobazar.wcquiz.feature_rating.ui

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_rating.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.core.base.ui.BaseFragment
import msk.pobazar.wcquiz.core.extensions.visible
import msk.pobazar.wcquiz.feature_rating.R
import msk.pobazar.wcquiz.feature_rating.presenter.RatingPresenter
import msk.pobazar.wcquiz.feature_rating.presenter.RatingView
import msk.pobazar.wcquiz.feature_rating.ui.adapter.RatingListAdapter
import msk.pobazar.wcquiz.view_error.ErrorType
import javax.inject.Inject

class RatingFragment : BaseFragment(), RatingView {
    override val layout: Int
        get() = R.layout.fragment_rating

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: RatingPresenter

    private val adapter = RatingListAdapter()

    override fun initUi() {
        rvRating.adapter = adapter
        rvRating.layoutManager = LinearLayoutManager(context)
    }

    override fun initUx() {
        errorRating.onActionClick { presenter.onRetryClick() }
        swipeRefresh.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    override fun setResults(results: List<ViewItem>) {
        adapter.replace(results)
    }

    override fun showError(type: ErrorType) {
        errorRating.type = type
        errorRating.visible(type != ErrorType.NONE)
    }

    override fun showProgressSwipeRefresh(isShow: Boolean) {
        swipeRefresh.isRefreshing = isShow
    }

    companion object {
        fun newInstance() = RatingFragment()
    }

}