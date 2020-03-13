package msk.pobazar.wcquiz.feature_game.presenter

import moxy.MvpView

interface GameView : MvpView {

    fun setCountQuestion(count: String)

    fun setQuestion(question: String)

    fun setAnswers(answers: List<String>)

    fun setImage(image: String)

    fun setTimerValue(progress: Int)
}