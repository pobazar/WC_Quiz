package msk.pobazar.wcquiz.data_remote.di

import msk.pobazar.wcquiz.data_remote.repo.QuestionsRepoRemoteImpl
import msk.pobazar.wcquiz.data_remote.repo.RecordsRepoRemoteImpl
import msk.pobazar.wcquiz.domain.repo.remote.QuestionsRepoRemote
import msk.pobazar.wcquiz.domain.repo.remote.RecordsRepoRemote
import toothpick.config.Module

class DataRemoteModule : Module() {
    init {
        bind(RecordsRepoRemote::class.java)
                .to(RecordsRepoRemoteImpl::class.java)
                .singleton()
        
        bind(QuestionsRepoRemote::class.java)
                .to(QuestionsRepoRemoteImpl::class.java)
                .singleton()
    }
}