package msk.pobazar.wcquiz.feature_game.presenter

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import msk.pobazar.wcquiz.core.analytics.AnalyticsKeys
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import msk.pobazar.wcquiz.domain.interactor.AnalyticsInteractor
import msk.pobazar.wcquiz.domain.interactor.ImageInteractor
import msk.pobazar.wcquiz.domain.interactor.QuestionsInteractor
import msk.pobazar.wcquiz.domain.interactor.ResultInteractor
import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.device.NetworkManager
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.feature_game.R
import msk.pobazar.wcquiz.feature_game.mapper.GameMapper
import msk.pobazar.wcquiz.feature_game.viewData.GameViewData
import msk.pobazar.wcquiz.view_error.ErrorType
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@InjectViewState
class GamePresenter @Inject constructor(
    private val params: GameParams,
    private val router: Router,
    private val resultInteractor: ResultInteractor,
    private val questionsInteractor: QuestionsInteractor,
    private val imageInteractor: ImageInteractor,
    private val resourceManager: ResourceManager,
    private val analyticsInteractor: AnalyticsInteractor,
    private val networkManager: NetworkManager,
    private val gameMapper: GameMapper
) : BasePresenter<GameView>() {

    private val countQuestions = resourceManager.getInteger(R.integer.count_questions)
    private val maxTime = resourceManager.getInteger(R.integer.time_to_answer)
    private val results: MutableList<GameResult.Result> = mutableListOf()
    private val pbColorStart = resourceManager.getColor(R.color.main_yellow)
    private val pbColorFinish = resourceManager.getColor(R.color.pb_finish)

    private var currentNumber = 0
    private var currentTime = 0
    private var countdownDisposable: Disposable? = null
    private var timeStart: Long = 0

    private lateinit var games: List<GameViewData>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        analyticsInteractor.reportEvent(AnalyticsKeys.START_GAME)
        loadGames()
        loadImages()
    }

    fun onAnswerClick(answer: String) {
        stopTimer()
        addAnswer(answer)
        setNextGame()
    }

    fun onRetryClick() {
        if (!::games.isInitialized)
            loadGames()
        loadImages()
    }

    private fun loadGames() {
        games = (params.theme?.let {
            questionsInteractor.getRandomLocal(countQuestions, it)
        } ?: questionsInteractor.getRandomLocal(countQuestions))
            .map(gameMapper::toViewData)
    }

    private fun setNextGame() {
        if (++currentNumber >= countQuestions) {
            resultInteractor.setResult(
                GameResult(
                    results = results,
                    time = System.currentTimeMillis() - timeStart
                )
            )
            showResults()
        } else
            setGame()
    }

    private fun setGame() {
        val game = games[currentNumber]
        viewState.setQuestion(game.question)
        viewState.setAnswers(game.answers.shuffled())
        viewState.setImage(game.imageUrl)

        viewState.setCountQuestion(getCountQuestion(currentNumber))
        startTimer()
    }

    private fun getCountQuestion(number: Int) =
        resourceManager.getString(R.string.count_question, number + 1, countQuestions)

    private fun loadImages() {
        if (networkManager.isAvailable())
            imageInteractor.load(
                urls = games.map { it.imageUrl }
            )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.showProgress(true)
                    viewState.showError(ErrorType.NONE)
                }
                .doOnTerminate {
                    viewState.showProgress(false)
                }
                .subscribeBy(
                    onComplete = {
                        timeStart = System.currentTimeMillis()
                        setGame()
                    },
                    onError = {
                        viewState.showError(ErrorType.ERROR_SERVER_UNAVAILABLE)
                        Timber.e(it)
                    }
                )
                .bind()
        else
            viewState.showError(ErrorType.ERROR_NETWORK_UNAVAILABLE)
    }

    private fun startTimer() {
        countdownDisposable = Observable.interval(0, TICK.toLong(), TimeUnit.MILLISECONDS)
            .doOnSubscribe {
                currentTime = 0
                viewState.setTimer(0, pbColorStart)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    currentTime += TICK
                    viewState.setTimer(
                        progress = currentTime,
                        color = if (currentTime < PERCENT_FINISH_COLOR * maxTime)
                            pbColorStart
                        else
                            pbColorFinish
                    )
                    if (currentTime == maxTime) {
                        stopTimer()
                        addAnswer("")
                        setNextGame()
                    }
                }
            )
    }

    private fun stopTimer() {
        countdownDisposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }

    private fun addAnswer(answer: String) {
        if (currentNumber < countQuestions) {
            val game = games[currentNumber]
            results.add(
                GameResult.Result(
                    question = game.question,
                    answer = answer,
                    answerRight = game.answerRight,
                    image = game.imageUrl
                )
            )
        }
    }

    private fun showResults() {
        analyticsInteractor.reportEvent(AnalyticsKeys.FINISH_GAME)
        with(resultInteractor.getResult()) {
            analyticsInteractor.reportEvent(AnalyticsKeys.RESULT, "{\"countRight\":\"$countRight\", \"countAll\":\"$countAll\"}")
        }
        router.replace(NavigationScreen.Result)
    }

    companion object {
        private const val TICK = 100
        private const val PERCENT_FINISH_COLOR = 0.75F
    }
}