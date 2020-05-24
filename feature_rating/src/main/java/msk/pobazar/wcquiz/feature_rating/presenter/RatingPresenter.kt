package msk.pobazar.wcquiz.feature_rating.presenter

import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.domain.interactor.RatingInteractor
import msk.pobazar.wcquiz.domain.interactor.UserInteractor
import msk.pobazar.wcquiz.domain.repo.device.NetworkManager
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.feature_rating.R
import msk.pobazar.wcquiz.feature_rating.mapper.RatingMapper
import msk.pobazar.wcquiz.view_error.ErrorType
import javax.inject.Inject

@InjectViewState
class RatingPresenter @Inject constructor(
    private val router: Router,
    private val ratingInteractor: RatingInteractor,
    private val userInteractor: UserInteractor,
    private val networkManager: NetworkManager,
    private val resourceManager: ResourceManager,
    private val ratingMapper: RatingMapper
) : BasePresenter<RatingView>() {

    private var isShowTitleToolbar: Boolean = false

    private var isRefresh = true
        set(value) {
            field = value
            viewState.showProgressSwipeRefresh(value)
        }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRating()
    }

    fun onRetryClick() {
        loadRating()
    }

    fun onRefresh() {
        isRefresh = true
        loadRating()
    }

    fun onClickBack() {
        router.exit()
    }

    fun onScrolled(offset: Int) {
        viewState.showToolbarTitle(offset > 0)
    }

    private fun loadRating() {
        if (networkManager.isAvailable()) {
            Observables.zip(
                ratingInteractor.getLimit(resourceManager.getInteger(R.integer.count_ratings_top)),
                ratingInteractor.getById(userInteractor.getUser().id)
            )
                .doOnSubscribe {
                    viewState.showError(ErrorType.NONE)
                    isRefresh = true
                }
                .subscribeBy(
                    onNext = {
                        isRefresh = false
                        val results: MutableList<ViewItem> = mutableListOf()
                        viewState.setResults(
                            with(ratingMapper.toViewData(it)) {
                                results.add(title)
                                results.addAll(items)
                                results
                            }
                        )
                    },
                    onError = {
                        isRefresh = false
                        viewState.showError(ErrorType.ERROR_SERVER_UNAVAILABLE)
                    }
                )
                .bind()
        } else
            viewState.showError(ErrorType.ERROR_NETWORK_UNAVAILABLE)
    }
}