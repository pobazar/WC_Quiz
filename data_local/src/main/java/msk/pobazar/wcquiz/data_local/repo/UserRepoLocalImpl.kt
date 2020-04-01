package msk.pobazar.wcquiz.data_local.repo

import android.content.SharedPreferences
import msk.pobazar.wcquiz.data_local.di.User
import msk.pobazar.wcquiz.data_local.storage.string
import msk.pobazar.wcquiz.domain.repo.local.UserRepoLocal
import javax.inject.Inject

class UserRepoLocalImpl @Inject constructor(
    @User
    private val sharedPreferences: SharedPreferences
) : UserRepoLocal {

    override var name: String by sharedPreferences.string(key = { NAME })
    override var id: String by sharedPreferences.string(key = { ID })


    companion object {
        private const val NAME = "name"
        private const val ID = "id"
    }
}