package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.domain.model.Rating

interface RatingRepoRemote {

    fun getAll(): Observable<List<Rating>>

    fun getLimit(limit: Int): Observable<List<Rating>>

    fun getById(id: String): Observable<Rating>

    fun setNew(rating: Rating, countAll: Int, winStrick: Int, id: String): Completable

    fun updateUserName(name: String, id: String): Completable
}