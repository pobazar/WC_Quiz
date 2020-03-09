package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.Record
import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User
import msk.pobazar.wcquiz.domain.repo.remote.RecordsRepoRemote
import javax.inject.Inject

class RecordsInteractor @Inject constructor(
    private val recordsRepoRemote: RecordsRepoRemote
) {
    fun getAll(): Single<List<Record>> {
        return recordsRepoRemote
            .getAllRecords()
            .observeOn(Schedulers.io())
    }
    
    fun setNew(user: User, score: Score): Completable {
        return recordsRepoRemote
            .setNewRecord(
                user = user,
                score = score
            )
            .observeOn(Schedulers.io())
    }
}