package msk.pobazar.wcquiz.data_local.di

import android.content.Context
import android.content.SharedPreferences
import msk.pobazar.wcquiz.data_local.repo.QuestionRepoLocalImpl
import msk.pobazar.wcquiz.data_local.repo.ResultRepoLocalImpl
import msk.pobazar.wcquiz.data_local.repo.UserRepoLocalImpl
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import msk.pobazar.wcquiz.domain.repo.local.UserRepoLocal
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

        bind(UserRepoLocal::class.java)
            .to(UserRepoLocalImpl::class.java)
            .singleton()

        bind(SharedPreferences::class.java)
            .withName(Results::class.java)
            .toInstance(context.getSharedPreferences(PREFS_RESULT, Context.MODE_PRIVATE))

        bind(SharedPreferences::class.java)
            .withName(User::class.java)
            .toInstance(context.getSharedPreferences(PREFS_USER, Context.MODE_PRIVATE))
    }

    companion object {
        private const val PREFS_RESULT = "prefs_result"
        private const val PREFS_USER = "prefs_user"
    }
}