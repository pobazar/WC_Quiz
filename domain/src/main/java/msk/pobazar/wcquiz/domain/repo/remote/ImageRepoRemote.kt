package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Completable
import io.reactivex.Observable

interface ImageRepoRemote {

    fun loadImage(url: String): Observable<Completable>
}