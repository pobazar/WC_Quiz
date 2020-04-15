package msk.pobazar.wcquiz.database_firebase.api

import com.google.firebase.database.DatabaseReference
import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.api.RatingApi
import msk.pobazar.wcquiz.data_remote.models.RatingResponse
import msk.pobazar.wcquiz.database_firebase.FirebaseQuery
import javax.inject.Inject

class RatingApiImpl @Inject constructor(
    private val reference: DatabaseReference,
    private val firebaseQuery: FirebaseQuery
) : RatingApi {

    override fun readAll(): Observable<List<RatingResponse>> =
        firebaseQuery.read(
            reference
                .child(RESULT_PATH)
                .orderByChild(SCORE)
        )

    override fun readLimit(limit: Int): Observable<List<RatingResponse>> =
        firebaseQuery.read(
            reference
                .child(RESULT_PATH)
                .orderByChild(SCORE)
                .limitToLast(limit)
        )

    override fun readById(id: String): Observable<List<RatingResponse>> =
        firebaseQuery.read(
            reference
                .child(RESULT_PATH)
                .child(id)
        )

    override fun write(data: RatingResponse, id: String): Completable =
        firebaseQuery.write(
            reference
                .child(RESULT_PATH)
                .child(id)
                .setValue(data)
        )

    override fun updateUserName(data: String, id: String): Completable =
        firebaseQuery.write(
            reference
                .child(RESULT_PATH)
                .child(id)
                .child(NAME)
                .setValue(data)
        )

    companion object {
        private const val RESULT_PATH = "results"
        private const val NAME = "name"
        private const val SCORE = "score"
    }
}