package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Completable
import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Record
import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User
import msk.pobazar.wcquiz.domain.repo.remote.RecordRepoRemote
import java.util.Date

class RecordRepoRemoteImpl : RecordRepoRemote {
    override fun getAllRecords(): Single<List<Record>> {
        return Single.just(
            listOf(
                Record(
                    User("name1", "email1", Score(100)),
                    Score(100),
                    Date()
                ),
                Record(
                    User("name2", "email2", Score(200)),
                    Score(200),
                    Date()
                )
            )
        )
    }
    
    override fun setNewRecord(user: User, score: Score): Completable {
        return Completable.complete()
    }
}