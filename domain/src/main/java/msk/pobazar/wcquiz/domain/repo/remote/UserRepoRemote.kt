package msk.pobazar.wcquiz.domain.repo.remote

import msk.pobazar.wcquiz.domain.model.Score
import msk.pobazar.wcquiz.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepoRemote {
    fun getUser(id: String): Single<User>
    
    fun setScore(user: User, score: Score): Completable
}