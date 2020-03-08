package com.example.domain.RepoRemote

import com.example.domain.model.Score
import com.example.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepoRemote {
    fun getUser(id: String): Single<User>
    
    fun setScore(user: User, score: Score): Completable
}