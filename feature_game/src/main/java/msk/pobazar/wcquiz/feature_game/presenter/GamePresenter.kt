package msk.pobazar.wcquiz.feature_game.presenter

import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class GamePresenter @Inject constructor(

) : BasePresenter<GameView>() {
    override fun attachView(view: GameView?) {
        super.attachView(view)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onAnswerClick(answer: String) {

    }
}