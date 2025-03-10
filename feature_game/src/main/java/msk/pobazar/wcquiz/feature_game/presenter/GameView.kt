package msk.pobazar.wcquiz.feature_game.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import msk.pobazar.wcquiz.view_error.ErrorType

@StateStrategyType(AddToEndStrategy::class)
interface GameView : MvpView {

    fun setCountQuestion(count: String)

    fun setQuestion(question: String)

    fun setAnswers(answers: List<String>)

    fun setImage(image: String)

    fun setTimer(progress: Int, color: Int)

    fun showError(type: ErrorType)

    fun showProgress(isShow: Boolean)
}