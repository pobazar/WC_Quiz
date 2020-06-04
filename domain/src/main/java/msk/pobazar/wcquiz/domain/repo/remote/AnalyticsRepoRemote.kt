package msk.pobazar.wcquiz.domain.repo.remote

interface AnalyticsRepoRemote {

    fun reportEvent(eventName: String, json: String)
    fun reportEvent(eventName: String)
}