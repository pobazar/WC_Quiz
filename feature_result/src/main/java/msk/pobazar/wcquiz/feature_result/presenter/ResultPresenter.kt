package msk.pobazar.wcquiz.feature_result.presenter

import android.app.Activity
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import msk.pobazar.wcquiz.adMob.PageAds
import msk.pobazar.wcquiz.adMob.RewardAds
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import msk.pobazar.wcquiz.domain.interactor.RatingInteractor
import msk.pobazar.wcquiz.domain.interactor.ResultInteractor
import msk.pobazar.wcquiz.feature_result.mapper.ResultMapper
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import java.util.Date
import javax.inject.Inject

class ResultPresenter @Inject constructor(
    private val router: Router,
    private val resultInteractor: ResultInteractor,
    private val ratingInteractor: RatingInteractor,
    private val resultMapper: ResultMapper,
    private val rewardAds: RewardAds,
    private val pageAds: PageAds
) : BasePresenter<ResultView>() {

    private lateinit var resultViewData: ResultViewData

    override fun attachView(view: ResultView?) {
        super.attachView(view)
        pageAds.show()
        resultInteractor.isShowAnswer = false
        setupResult()
        sendRating()
    }

    fun onAgainPlayClick() {
        rewardAds.loadAds()
        router.replace(NavigationScreen.Game(GameParams()))
    }

    fun onShowAnswers(activity: Activity) {
        if (!resultInteractor.isShowAnswer)
            rewardAds.show(
                activity = activity,
                adCallback = object : RewardedAdCallback() {
                    override fun onRewardedAdOpened() {
                        // Ad opened.
                    }

                    override fun onRewardedAdClosed() {
                        // Ad closed.
                    }

                    override fun onUserEarnedReward(reward: RewardItem) {
                        // User earned reward.
                        resultInteractor.isShowAnswer = true
                        setupResult()
                    }

                    override fun onRewardedAdFailedToShow(errorCode: Int) {
                        // Ad failed to display.
                    }
                }
            )
    }

    fun onClickBack() {
        router.exit()
    }

    private fun setupResult() {
        resultViewData = resultMapper.toViewData(
            gameResults = resultInteractor.getResult(),
            isShowAnswer = resultInteractor.isShowAnswer
        )
        viewState.setEnableShowAnswer(!resultInteractor.isShowAnswer)
        viewState.setResults(resultViewData.items)
        viewState.setCountRight(resultViewData.title)
    }

    private fun sendRating() {
        with(resultInteractor.getResult()) {
            ratingInteractor.update(
                countRight = countRight,
                countAll = results.size,
                time = time,
                winStrick = winStrick,
                date = Date()
            )
                .subscribe()
                .bind()
        }
    }
}