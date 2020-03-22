package msk.pobazar.wcquiz.data_remote.repo

import android.net.Uri
import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.api.ImageApi
import msk.pobazar.wcquiz.domain.repo.remote.ImageRepoRemote
import javax.inject.Inject

class ImageRepoRemoteImpl @Inject constructor(
    private val imageApi: ImageApi
) : ImageRepoRemote {

    override fun loadImage(uri: String): Observable<Completable> =
        imageApi.loadImage(uri)
}