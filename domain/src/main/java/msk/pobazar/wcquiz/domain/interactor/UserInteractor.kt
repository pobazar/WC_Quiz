package msk.pobazar.wcquiz.domain.interactor

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import msk.pobazar.wcquiz.domain.model.User
import msk.pobazar.wcquiz.domain.repo.local.UserRepoLocal
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import java.util.UUID
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepoLocal: UserRepoLocal,
    private val ratingRepoRemote: RatingRepoRemote
) {

    fun getUser(): User {
        return if (userRepoLocal.id == "") {
            val id = UUID.randomUUID().toString().takeLast(17)
            userRepoLocal.name = id
            userRepoLocal.id = id
            User(
                name = id,
                id = id
            )
        } else
            User(
                name = userRepoLocal.name,
                id = userRepoLocal.id
            )
    }

    fun updateName(name: String): Completable {
        userRepoLocal.name = name
        return ratingRepoRemote.updateUserName(name, getUser().id)
            .subscribeOn(Schedulers.io())
    }
}