package msk.pobazar.wcquiz.data_local.di

import msk.pobazar.wcquiz.data_local.repo.QuestionRepoLocalImpl
import msk.pobazar.wcquiz.data_local.repo.ResultRepoLocalImpl
import msk.pobazar.wcquiz.domain.repo.local.QuestionRepoLocal
import msk.pobazar.wcquiz.domain.repo.local.ResultRepoLocal
import toothpick.config.Module

class DataLocalModule : Module() {
    init {
        bind(QuestionRepoLocal::class.java)
            .to(QuestionRepoLocalImpl::class.java)
            .singleton()

        bind(ResultRepoLocal::class.java)
            .to(ResultRepoLocalImpl::class.java)
            .singleton()
    }
}