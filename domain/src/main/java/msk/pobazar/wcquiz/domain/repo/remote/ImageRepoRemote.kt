package msk.pobazar.wcquiz.domain.repo.remote

import android.net.Uri
import io.reactivex.Completable
import io.reactivex.Observable

interface ImageRepoRemote {

    fun loadImage(uri: String): Observable<Completable>
}