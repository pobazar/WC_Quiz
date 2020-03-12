package msk.pobazar.wcquiz.data_local.di

import msk.pobazar.wcquiz.data_local.repo.QuestionsRepoLocalImpl
import msk.pobazar.wcquiz.domain.repo.local.QuestionsRepoLocal
import toothpick.config.Module

class DataLocalModule : Module() {
    init {
        bind(QuestionsRepoLocal::class.java)
            .to(QuestionsRepoLocalImpl::class.java)
            .singleton()
    }
}