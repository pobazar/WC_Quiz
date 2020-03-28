package msk.pobazar.wcquiz.domain.repo.remote

import io.reactivex.Completable

interface ImageRepoRemote {

    fun loadImage(uri: String): Completable
}