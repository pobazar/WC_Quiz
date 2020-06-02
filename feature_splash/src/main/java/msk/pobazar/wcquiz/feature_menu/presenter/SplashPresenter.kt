package msk.pobazar.wcquiz.feature_menu.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import msk.pobazar.wcquiz.adMob.PageAds
import msk.pobazar.wcquiz.adMob.RewardAds
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.domain.interactor.QuestionsInteractor
import msk.pobazar.wcquiz.domain.repo.device.NetworkManager
import msk.pobazar.wcquiz.view_error.ErrorType
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val router: Router,
    private val questionsInteractor: QuestionsInteractor,
    private val networkManager: NetworkManager,
    private val pageAds: PageAds,
    private val rewardAds: RewardAds
) : BasePresenter<SplashView>() {

    override fun attachView(view: SplashView?) {
        super.attachView(view)
        loadData()
    }

    fun onRetryClick() {
        loadData()
    }

    private fun loadData() {
        if (networkManager.isAvailable()) {
            loadAds()
            questionsInteractor.getAllRemoteAndToLocal()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.showProgress(true)
                    viewState.showError(ErrorType.NONE)
                }
                .subscribeBy(
                    onNext = {
                        viewState.showProgress(false)
                        router.setRoot(NavigationScreen.MainMenu)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(ErrorType.ERROR_NETWORK_UNAVAILABLE)
                        Timber.e(it)
                    }
                )
                .bind()
        } else {
            viewState.showProgress(false)
            viewState.showError(ErrorType.ERROR_NETWORK_UNAVAILABLE)
        }
    }

    private fun loadAds() {
        pageAds.create()
        rewardAds.loadAds()
    }
}