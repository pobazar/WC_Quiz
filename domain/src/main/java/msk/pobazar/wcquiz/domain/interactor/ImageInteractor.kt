package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.repo.remote.ImageRepoRemote
import javax.inject.Inject

class ImageInteractor @Inject constructor(
    private val imageRepoRemote: ImageRepoRemote
) {

    fun load(urls: List<String>): Completable =
        Observable.fromIterable(
            urls
        )
            .flatMapCompletable { uri ->
                imageRepoRemote.loadImage(uri)
            }
            .subscribeOn(Schedulers.io())
}