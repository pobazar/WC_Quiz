package msk.pobazar.wcquiz.data_remote.di

import msk.pobazar.wcquiz.data_remote.repo.QuestionsRepoRemoteImpl
import msk.pobazar.wcquiz.data_remote.repo.RatingRepoRemoteImpl
import msk.pobazar.wcquiz.domain.repo.remote.QuestionsRepoRemote
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import toothpick.config.Module

class DataRemoteModule : Module() {
    init {
        bind(RatingRepoRemote::class.java)
            .to(RatingRepoRemoteImpl::class.java)
            .singleton()

        bind(QuestionsRepoRemote::class.java)
            .to(QuestionsRepoRemoteImpl::class.java)
            .singleton()
    }
}