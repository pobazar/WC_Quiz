package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.model.User
import msk.pobazar.wcquiz.domain.repo.local.UserRepoLocal
import msk.pobazar.wcquiz.domain.repo.remote.RatingRepoRemote
import java.util.UUID
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userInteractor: UserInteractor,
    private val userRepoLocal: UserRepoLocal,
    private val ratingRepoRemote: RatingRepoRemote
) {

    fun getUser(): User {
        return if (userRepoLocal.id == "") {
            val id = UUID.randomUUID().toString()
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

    fun setName(name: String) {
        userRepoLocal.name = name
        ratingRepoRemote.updateUserName(name, userInteractor.getUser().id)
    }
}