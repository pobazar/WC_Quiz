package msk.pobazar.wcquiz.feature_game.presenter

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.screens.NavigationScreen
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
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@InjectViewState
class GamePresenter @Inject constructor(
    private val router: Router,
    private val resultInteractor: ResultInteractor,
    private val questionsInteractor: QuestionsInteractor,
    private val imageInteractor: ImageInteractor,
    private val resourceManager: ResourceManager,
    private val networkManager: NetworkManager,
    private val gameMapper: GameMapper
) : BasePresenter<GameView>() {

    private val countQuestions = resourceManager.getInteger(R.integer.count_questions)
    private val maxTime = resourceManager.getInteger(R.integer.time_to_answer)
    private var currentNumber = 0
    private val results: MutableList<GameResult> = mutableListOf()

    private var currentTime = 0
    private var countdownDisposable: Disposable? = null

    private lateinit var games: List<GameViewData>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
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
        games = questionsInteractor.getRandomLocal(countQuestions)
            .map(gameMapper::mapToGameViewData)
    }

    private fun setNextGame() {
        if (++currentNumber >= countQuestions) {
            resultInteractor.setResult(results)
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
        if (networkManager.isNetworkAvailable()) {
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
                        setGame()
                    },
                    onError = {
                        viewState.showError(ErrorType.ERROR_SERVER_UNAVAILABLE)
                    }
                )
                .bind()
        } else {
            viewState.showError(ErrorType.ERROR_NETWORK_UNAVAILABLE)
        }
    }

    private fun startTimer() {
        countdownDisposable = Observable.interval(0, TICK.toLong(), TimeUnit.MILLISECONDS)
            .doOnSubscribe {
                currentTime = maxTime
                viewState.setTimerValue(maxTime)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    currentTime -= TICK
                    viewState.setTimerValue(currentTime)
                    if (currentTime == 0) {
                        addAnswer("")
                        setNextGame()
                        stopTimer()
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
        val game = games[currentNumber]
        results.add(
            GameResult(
                question = game.question,
                answer = answer,
                answerRight = game.answerRight,
                image = game.imageUrl
            )
        )
    }

    private fun showResults() {
        router.replace(NavigationScreen.Result)
    }

    companion object {
        private const val TICK = 100
    }
}