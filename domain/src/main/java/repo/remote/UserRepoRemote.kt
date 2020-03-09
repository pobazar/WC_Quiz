package repo.remote

import msk.pobazar.wcquiz.model.Score
import msk.pobazar.wcquiz.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepoRemote {
    fun getUser(id: String): Single<User>
    
    fun setScore(user: User, score: Score): Completable
}