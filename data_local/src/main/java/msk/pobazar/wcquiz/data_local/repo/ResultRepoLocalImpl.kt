package msk.pobazar.wcquiz.data_local.repo

import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import javax.inject.Inject

class ResultRepoLocalImpl @Inject constructor(

) : ResultRepoLocal {
    override fun getResult(): List<GameResult> {
        TODO("Not yet implemented")
    }

    override fun setResult(results: List<GameResult>) {
        TODO("Not yet implemented")
    }
}