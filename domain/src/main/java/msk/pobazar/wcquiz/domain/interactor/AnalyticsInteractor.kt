package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.repo.remote.AnalyticsRepoRemote
import javax.inject.Inject

class AnalyticsInteractor @Inject constructor(
    private val analyticsRepoRemote: AnalyticsRepoRemote
) {

    fun reportEvent(eventName: String, json: String) {
        analyticsRepoRemote.reportEvent(
            eventName = eventName,
            json = json
        )
    }

    fun reportEvent(eventName: String) {
        analyticsRepoRemote.reportEvent(
            eventName = eventName
        )
    }
}