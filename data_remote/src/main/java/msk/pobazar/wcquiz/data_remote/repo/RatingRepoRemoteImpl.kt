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

    override fun getAll(): Observable<List<Rating>> {
        return ratingApi.readAll()
            .map {
                it.map(ratingApiMapper::toDomain)
            }
    }

    override fun getLimit(limit: Int): Observable<List<Rating>> {
        return ratingApi.readLimit(limit)
            .map {
                it.map(ratingApiMapper::toDomain)
            }
    }

    override fun getById(id: String): Observable<Rating> {
        return ratingApi.readById(id)
            .map {
                ratingApiMapper.toDomain(it)
            }
    }

    override fun setNew(rating: Rating, countAll: Int, winStrick: Int, id: String): Completable {
        return ratingApi.write(
            data = ratingApiMapper.toApi(
                rating = rating,
                countAll = countAll,
                winStrick = winStrick
            ),
            id = id
        )
    }

    override fun updateUserName(name: String, id: String): Completable {
        return ratingApi.updateUserName(
            data = name,
            id = id
        )
    }
}