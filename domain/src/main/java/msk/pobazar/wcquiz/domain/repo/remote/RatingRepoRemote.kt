package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Completable
import io.reactivex.Single
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User

interface RatingRepoRemote {

    fun getAllRating(): Single<List<Rating>>

    fun setNewRating(user: User, score: Score): Completable
}