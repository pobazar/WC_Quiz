package msk.pobazar.wcquiz.storage_paper.di

import msk.pobazar.wcquiz.data_local.storage.LocalStorage
import msk.pobazar.wcquiz.storage_paper.PaperLocalStorage
import toothpick.config.Module

class StorageModule() : Module() {

    init {
        bind(LocalStorage::class.java)
            .to(PaperLocalStorage::class.java)
            .singleton()
    }
}