package msk.pobazar.wcquiz.domain.repo.local

import msk.pobazar.wcquiz.domain.model.GameResult

interface ResultRepoLocal {

    var isShowAnswer: Boolean

    fun getResult(): List<GameResult>

    fun setResult(results: List<GameResult>)
}