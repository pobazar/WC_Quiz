package msk.pobazar.wcquiz.feature_rating.presenter

import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.domain.interactor.RatingInteractor
import msk.pobazar.wcquiz.domain.repo.device.NetworkManager
import msk.pobazar.wcquiz.feature_rating.mapper.RatingMapper
import msk.pobazar.wcquiz.view_error.ErrorType
import javax.inject.Inject

@InjectViewState
class RatingPresenter @Inject constructor(
    private val ratingInteractor: RatingInteractor,
    private val networkManager: NetworkManager,
    private val ratingMapper: RatingMapper
) : BasePresenter<RatingView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRating()
    }

    fun onRetryClick() {
        loadRating()
    }

    private fun loadRating() {
        if (networkManager.isAvailable())
            ratingInteractor.getAll()
                .doOnSubscribe {
                    viewState.showError(ErrorType.NONE)
                    viewState.showProgress(true)
                }
                .subscribeBy(
                    onNext = {
                        viewState.showProgress(false)
                        viewState.setResults(
                            ratingMapper.toViewData(it).items
                        )
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(ErrorType.ERROR_SERVER_UNAVAILABLE)
                    }
                )
                .bind()
        else
            viewState.showError(ErrorType.ERROR_NETWORK_UNAVAILABLE)
    }
}