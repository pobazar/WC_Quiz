package msk.pobazar.wcquiz.data_local.repo

import android.content.SharedPreferences
import msk.pobazar.wcquiz.data_local.di.Results
import msk.pobazar.wcquiz.data_local.storage.LocalStorage
import msk.pobazar.wcquiz.data_local.storage.boolean
import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import javax.inject.Inject

class ResultRepoLocalImpl @Inject constructor(
    private val localStorage: LocalStorage,
    @Results
    private val sharedPreferences: SharedPreferences
) : ResultRepoLocal {

    override var isShowAnswer: Boolean by sharedPreferences.boolean(key = { IS_SHOW_ANSWER }, defaultValue = false)

    override fun getResult(): List<GameResult> {
        return localStorage.read(
            tableName = RESULT_BOOK,
            key = RESULT
        )
    }

    override fun setResult(results: List<GameResult>) {
        localStorage.write(
            tableName = RESULT_BOOK,
            key = RESULT,
            data = results
        )
    }

    companion object {
        private const val RESULT_BOOK = "result book"
        private const val RESULT = "result page"

        private const val IS_SHOW_ANSWER = "is show answer"
    }
}