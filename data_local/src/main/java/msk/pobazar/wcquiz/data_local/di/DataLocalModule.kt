package msk.pobazar.wcquiz.data_local.di

import android.content.Context
import android.content.SharedPreferences
import msk.pobazar.wcquiz.data_local.repo.QuestionRepoLocalImpl
import msk.pobazar.wcquiz.data_local.repo.ResultRepoLocalImpl
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import toothpick.config.Module

class DataLocalModule(
    context: Context
) : Module() {
    init {
        bind(QuestionRepoLocal::class.java)
            .to(QuestionRepoLocalImpl::class.java)
            .singleton()

        bind(ResultRepoLocal::class.java)
            .to(ResultRepoLocalImpl::class.java)
            .singleton()

        bind(SharedPreferences::class.java)
            .withName(Results::class.java)
            .toInstance(context.getSharedPreferences(PREFS_RESULT, Context.MODE_PRIVATE))
    }

    companion object {
        private const val PREFS_RESULT = "prefs_result"
    }
}