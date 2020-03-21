package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import javax.inject.Inject

class ResultInteractor @Inject constructor(
    private val resultRepoLocal: ResultRepoLocal
) {
    var isShowAnswer
        get() = resultRepoLocal.isShowAnswer
        set(value) {
            resultRepoLocal.isShowAnswer = value
        }

    fun getResult(): List<GameResult> {
        return resultRepoLocal.getResult()
    }

    fun setResult(results: List<GameResult>) {
        resultRepoLocal.setResult(results)
    }


}