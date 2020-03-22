package msk.pobazar.wcquiz.data_remote.api

import android.net.Uri
import io.reactivex.Completable
import io.reactivex.Observable

interface ImageApi {

    fun getUri(url: String): Observable<String>

    fun loadImage(uri: String): Observable<Completable>
}