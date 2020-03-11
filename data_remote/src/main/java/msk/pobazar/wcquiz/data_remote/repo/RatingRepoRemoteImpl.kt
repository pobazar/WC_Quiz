package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Completable
import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import java.util.Date

class RatingRepoRemoteImpl : RatingRepoRemote {
    override fun getAllRating(): Single<List<Rating>> {
        return Single.just(
            listOf(
                Rating(
                    User("name1", "email1", Score(100)),
                    Score(100),
                    Date()
                ),
                Rating(
                    User("name2", "email2", Score(200)),
                    Score(200),
                    Date()
                )
            )
        )
    }
    
    override fun setNewRating(user: User, score: Score): Completable {
        return Completable.complete()
    }
}