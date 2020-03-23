package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.Completable
import io.reactivex.Observable

interface ImageApi {

    fun getUri(urls: List<String>): Observable<List<String>>

    fun loadImage(uri: String): Observable<Completable>
}