package msk.pobazar.wcquiz.data_remote.di

import msk.pobazar.wcquiz.data_remote.repo.QuestionsRepoRemoteImpl
import msk.pobazar.wcquiz.data_remote.repo.RecordRepoRemoteImpl
import msk.pobazar.wcquiz.domain.repo.remote.QuestionsRepoRemote
import msk.pobazar.wcquiz.domain.repo.remote.RecordRepoRemote
import toothpick.config.Module

class DataRemoteModule : Module() {
    init {
        bind(RecordRepoRemote::class.java)
                .to(RecordRepoRemoteImpl::class.java)
                .singleton()
        
        bind(QuestionsRepoRemote::class.java)
                .to(QuestionsRepoRemoteImpl::class.java)
                .singleton()
    }
}