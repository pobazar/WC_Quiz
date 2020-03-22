package msk.pobazar.wcquiz.database_firebase.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import msk.pobazar.wcquiz.data_remote.api.QuestionApi
import msk.pobazar.wcquiz.database_firebase.api.QuestionApiImpl
import toothpick.config.Module

class ApiModule : Module() {

    init {
        bind(DatabaseReference::class.java)
            .toInstance(Firebase.database.reference)

        bind(QuestionApi::class.java)
            .to(QuestionApiImpl::class.java)
            .singleton()
    }
}