package msk.pobazar.wcquiz.domain.repo.local

import msk.pobazar.wcquiz.domain.model.GameResult

interface ResultRepoLocal {

    fun getResult(): List<GameResult>

    fun setResult(results: List<GameResult>)
}