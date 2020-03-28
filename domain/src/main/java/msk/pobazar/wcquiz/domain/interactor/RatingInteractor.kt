package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import java.util.Date
import javax.inject.Inject

class RatingInteractor @Inject constructor(
    private val ratingRepoRemote: RatingRepoRemote
) {
    fun getAll(): Observable<List<Rating>> {
        return ratingRepoRemote
            .getAllRating()
            .subscribeOn(Schedulers.io())
    }

    fun setNew(user: String, countRight: Int, countAll: Int, time: Long, winStrick: Int, date: Date): Completable {
        return ratingRepoRemote
            .setNewRating(
                rating = Rating(
                    countRight = countRight,
                    score = calculationScore(countRight, countAll, time, winStrick),
                    time = time,
                    user = user,
                    date = date
                ),
                countAll = countAll,
                winStrick = winStrick
            )
            .subscribeOn(Schedulers.io())
    }

    private fun calculationScore(countRight: Int, countAll: Int, time: Long, winStrick: Int): Float {
        return ((countRight.toFloat() / countAll) / time) * winStrick * 10_000_000F
    }
}