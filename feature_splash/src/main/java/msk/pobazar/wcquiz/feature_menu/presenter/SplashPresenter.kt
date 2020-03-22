package msk.pobazar.wcquiz.feature_menu.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.domain.interactor.QuestionsInteractor
import msk.pobazar.wcquiz.domain.repo.device.NetworkManager
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val router: Router,
    private val questionsInteractor: QuestionsInteractor,
    private val networkManager: NetworkManager
) : BasePresenter<SplashView>() {

    override fun attachView(view: SplashView?) {
        super.attachView(view)
        loadData()
    }

    fun onRetryClick() {
        loadData()
    }

    private fun loadData() {
        if (networkManager.isAvailable())
            questionsInteractor.getAllRemoteAndToLocal()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.showProgress(true)
                    viewState.showError(false)
                }
                .subscribeBy(
                    onNext = {
                        viewState.showProgress(false)
                        router.setRoot(NavigationScreen.MainMenu)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(true)
                        Timber.e(it)
                    }
                )
                .bind()
        else {
            viewState.showProgress(false)
            viewState.showError(true)
        }
    }
}