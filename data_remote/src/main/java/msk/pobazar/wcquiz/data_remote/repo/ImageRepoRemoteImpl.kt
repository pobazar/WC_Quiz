package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Completable
import msk.pobazar.wcquiz.data_remote.api.ImageApi
import msk.pobazar.wcquiz.domain.repo.remote.ImageRepoRemote
import javax.inject.Inject

class ImageRepoRemoteImpl @Inject constructor(
    private val imageApi: ImageApi
) : ImageRepoRemote {

    override fun loadImage(uri: String): Completable =
        imageApi.loadImage(uri)
}