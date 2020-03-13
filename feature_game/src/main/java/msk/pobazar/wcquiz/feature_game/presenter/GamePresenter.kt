package msk.pobazar.wcquiz.feature_game.presenter

import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.domain.interactor.QuestionsInteractor
import msk.pobazar.wcquiz.domain.interactor.ResultInteractor
import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.feature_game.R
import msk.pobazar.wcquiz.feature_game.mapper.GameMapper
import msk.pobazar.wcquiz.feature_game.viewData.GameViewData
import javax.inject.Inject

@InjectViewState
class GamePresenter @Inject constructor(
    private val resultInteractor: ResultInteractor,
    private val questionsInteractor: QuestionsInteractor,
    private val gameMapper: GameMapper,
    private val resourceManager: ResourceManager
) : BasePresenter<GameView>() {

    private val countQuestions = resourceManager.getInteger(R.integer.count_questions)
    private val maxTime = resourceManager.getInteger(R.integer.time_to_answer)
    private var currentNumber = 0
    private val results: MutableList<GameResult> = mutableListOf()
    private lateinit var games: List<GameViewData>

    override fun attachView(view: GameView?) {
        super.attachView(view)
        loadGames()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setGame()
    }

    fun onAnswerClick(answer: String) {
        addAnswer(answer)
        if (++currentNumber >= countQuestions)
            showResults()
        else
            setGame()
    }

    private fun loadGames() {
        games = questionsInteractor.getRandomLocal(countQuestions)
            .map(gameMapper::mapToGameViewData)
    }

    private fun setGame() {
        val game = games[currentNumber]
        viewState.setQuestion(game.question)
        viewState.setAnswers(game.answers.shuffled())
        viewState.setImage(game.image)

        viewState.setCountQuestion(getCountQuestion(currentNumber))
        viewState.setTimerValue(maxTime)
    }

    private fun getCountQuestion(number: Int) =
        resourceManager.getString(R.string.count_question, number, countQuestions)

    private fun addAnswer(answer: String) {
        val game = games[currentNumber]
        results.add(
            GameResult(
                question = game.question,
                answer = answer,
                answerRight = game.answerRight,
                image = game.image
            )
        )
    }

    private fun showResults() {
        resultInteractor.setResult(results)
    }
}