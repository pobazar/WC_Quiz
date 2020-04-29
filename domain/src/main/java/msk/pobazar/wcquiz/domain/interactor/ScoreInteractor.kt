package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.model.GameResult
import toothpick.InjectConstructor

@InjectConstructor
class ScoreInteractor {

    fun calculate(countRight: Int, countAll: Int, time: Long, winStrick: Int): Float {
        return ((countRight.toFloat() / countAll) / time) * winStrick * 10_000_000F
    }

    fun calculate(gameResult: GameResult): Float {
        with(gameResult) {
            return ((countRight.toFloat() / countAll) / time) * winStrick * 10_000_000F
        }
    }
}