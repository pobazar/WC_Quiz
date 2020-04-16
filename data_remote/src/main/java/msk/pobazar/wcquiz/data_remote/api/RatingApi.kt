package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.models.RatingResponse

interface RatingApi {

    fun readAll(): Observable<List<RatingResponse>>

    fun readLimit(limit: Int): Observable<List<RatingResponse>>

    fun readById(id: String): Observable<RatingResponse>

    fun updateScore(data: RatingResponse, id: String): Completable

    fun updateUserName(data: String, id: String): Completable
}