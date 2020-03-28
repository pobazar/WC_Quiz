package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.domain.model.Rating

interface RatingRepoRemote {

    fun getAllRating(): Observable<List<Rating>>

    fun setNewRating(rating: Rating, countAll: Int, winStrick: Int, score: Float): Completable
}