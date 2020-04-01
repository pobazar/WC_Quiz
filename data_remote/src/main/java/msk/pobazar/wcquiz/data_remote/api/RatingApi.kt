package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.models.RatingResponse

interface RatingApi {

    fun readAll(): Observable<List<RatingResponse>>

    fun write(data: RatingResponse, id: String): Completable

    fun updateUserName(data: String, id: String): Completable
}