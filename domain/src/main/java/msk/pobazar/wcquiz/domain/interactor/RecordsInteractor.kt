package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import javax.inject.Inject

class RecordsInteractor @Inject constructor(
    private val ratingRepoRemote: RatingRepoRemote
) {
    fun getAll(): Single<List<Rating>> {
        return ratingRepoRemote
            .getAllRating()
            .observeOn(Schedulers.io())
    }

    fun setNew(user: User, score: Score): Completable {
        return ratingRepoRemote
            .setNewRating(
                user = user,
                score = score
            )
            .observeOn(Schedulers.io())
    }
}