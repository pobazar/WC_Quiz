package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import java.util.Date
import javax.inject.Inject

class RatingInteractor @Inject constructor(
    private val userInteractor: UserInteractor,
    private val ratingRepoRemote: RatingRepoRemote
) {

    fun getAll(): Observable<List<Rating>> {
        return ratingRepoRemote
            .getAll()
            .subscribeOn(Schedulers.io())
    }

    fun getLimit(limit: Int): Observable<List<Rating>> {
        return ratingRepoRemote
            .getLimit(limit)
            .subscribeOn(Schedulers.io())
    }

    fun getById(id: String): Observable<Rating> {
        return ratingRepoRemote
            .getById(id)
            .subscribeOn(Schedulers.io())
    }

    fun update(countRight: Int, countAll: Int, time: Long, winStrick: Int, date: Date): Completable {
        val id = userInteractor.getUser().id
        val score = calculationScore(countRight, countAll, time, winStrick)
        return getById(id)
            .flatMapCompletable {
                if (score > it.score)
                    ratingRepoRemote
                        .setNew(
                            rating = Rating(
                                countRight = countRight,
                                score = score,
                                time = time,
                                name = userInteractor.getUser().name,
                                date = date
                            ),
                            countAll = countAll,
                            winStrick = winStrick,
                            id = id
                        )
                        .subscribeOn(Schedulers.io())
                else
                    Completable.complete()
            }
    }

    private fun calculationScore(countRight: Int, countAll: Int, time: Long, winStrick: Int): Float {
        return ((countRight.toFloat() / countAll) / time) * winStrick * 10_000_000F
    }
}