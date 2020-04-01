package msk.pobazar.wcquiz.data_remote.api

import io.reactivex.Completable
import io.reactivex.Observable

interface ImageApi {

    fun getUri(urls: List<String>): Observable<Map<String, String>>

    fun loadImage(uri: String): Completable
}