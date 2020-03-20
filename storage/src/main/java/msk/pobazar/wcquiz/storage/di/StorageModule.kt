package msk.pobazar.wcquiz.storage.di

import msk.pobazar.wcquiz.data_local.storage.LocalStorage
import msk.pobazar.wcquiz.storage.PaperLocalStorage
import toothpick.config.Module

class StorageModule : Module() {
    init {
        bind(LocalStorage::class.java)
            .to(PaperLocalStorage::class.java)
            .singleton()
    }
}