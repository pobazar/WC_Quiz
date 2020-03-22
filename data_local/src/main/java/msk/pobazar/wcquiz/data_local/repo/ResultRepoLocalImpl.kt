package msk.pobazar.wcquiz.data_local.repo

import android.content.SharedPreferences
import msk.pobazar.wcquiz.data_local.di.Results
import msk.pobazar.wcquiz.data_local.storage.LocalStorage
import msk.pobazar.wcquiz.data_local.storage.boolean
import msk.pobazar.wcquiz.domain.model.GameResult
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import javax.inject.Inject

class ResultRepoLocalImpl @Inject constructor(
    private val storage: LocalStorage,
    @Results
    private val sharedPreferences: SharedPreferences
) : ResultRepoLocal {

    override var isShowAnswer: Boolean by sharedPreferences.boolean(key = { IS_SHOW_ANSWER }, defaultValue = false)

    override fun getResult(): List<GameResult> {
        return storage.read(
            tableName = RESULT_BOOK,
            key = RESULTS
        )
    }

    override fun setResult(results: List<GameResult>) {
        storage.write(
            tableName = RESULT_BOOK,
            key = RESULTS,
            data = results
        )
    }

    companion object {
        private const val RESULT_BOOK = "result book"
        private const val RESULTS = "results page"

        private const val IS_SHOW_ANSWER = "is show answer"
    }
}