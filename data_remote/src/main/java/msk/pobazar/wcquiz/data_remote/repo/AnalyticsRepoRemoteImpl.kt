package msk.pobazar.wcquiz.data_remote.repo

import com.yandex.metrica.YandexMetrica
import msk.pobazar.wcquiz.data_remote.BuildConfig
import msk.pobazar.wcquiz.domain.repo.remote.AnalyticsRepoRemote
import toothpick.InjectConstructor

@InjectConstructor
class AnalyticsRepoRemoteImpl(
) : AnalyticsRepoRemote {
    override fun reportEvent(eventName: String, json: String) {
        if (!BuildConfig.DEBUG)
            YandexMetrica.reportEvent(eventName, json)
    }

    override fun reportEvent(eventName: String) {
        if (!BuildConfig.DEBUG)
            YandexMetrica.reportEvent(eventName)
    }
}