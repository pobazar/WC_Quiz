package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.api.RatingApi
import msk.pobazar.wcquiz.data_remote.mapper.RatingApiMapper
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import javax.inject.Inject

class RatingRepoRemoteImpl @Inject constructor(
    private val ratingApi: RatingApi,
    private val ratingApiMapper: RatingApiMapper
) : RatingRepoRemote {
    override fun getAllRating(): Observable<List<Rating>> {
        return ratingApi.readAll()
            .map {
                it.map(ratingApiMapper::mapApiToRating)
            }
    }

    override fun setNewRating(rating: Rating, countAll: Int, winStrick: Int): Completable {
        return ratingApi.write(
            ratingApiMapper.mapRatingToApi(
                rating = rating,
                countAll = countAll,
                winStrick = winStrick
            )
        )
    }
}