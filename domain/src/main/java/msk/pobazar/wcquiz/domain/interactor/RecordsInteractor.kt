package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import javax.inject.Inject

class RecordsInteractor @Inject constructor(
    private val ratingRepoRemote: RatingRepoRemote
) {
    fun getAll(): Observable<List<Rating>> {
        return ratingRepoRemote
            .getAllRating()
            .subscribeOn(Schedulers.io())
    }

    fun setNew(rating: Rating, countAll: Int, winStrick: Int, score: Float): Completable {
        return ratingRepoRemote
            .setNewRating(
                rating = rating,
                countAll = countAll,
                winStrick = winStrick,
                score = score
            )
            .subscribeOn(Schedulers.io())
    }
}