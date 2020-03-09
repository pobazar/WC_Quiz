package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Completable
import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Record
import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User

interface RecordsRepoRemote {
    fun getAllRecords(): Single<List<Record>>
    
    fun setNewRecord(user: User, score: Score): Completable
}