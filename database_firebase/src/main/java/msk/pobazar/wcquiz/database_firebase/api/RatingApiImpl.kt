package msk.pobazar.wcquiz.database_firebase.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.data_remote.api.RatingApi
import msk.pobazar.wcquiz.data_remote.models.RatingResponse
import timber.log.Timber
import javax.inject.Inject

class RatingApiImpl @Inject constructor(
    private val reference: DatabaseReference
) : RatingApi {

    override fun readAll(): Observable<List<RatingResponse>> {
        val ratingsSubject: PublishSubject<List<RatingResponse>> = PublishSubject.create()

        val query: Query = reference.child(RESULT_PATH).orderByChild(RESULT_PATH)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<RatingResponse>()
                for (singleSnapshot in dataSnapshot.children) {
                    val data = singleSnapshot.getValue(RatingResponse::class.java)
                    data?.let { list.add(data) }
                    Timber.d("firebase: $data")
                }
                ratingsSubject.onNext(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                ratingsSubject.onError(Throwable(databaseError.toException()))
                Timber.e("onCancelled: ${databaseError.toException()}")
            }
        })
        return ratingsSubject
    }

    override fun write(data: RatingResponse): Completable {
        val ratingsSubject: PublishSubject<Completable> = PublishSubject.create()

        reference.child(RESULT_PATH).setValue(data)
            .addOnCompleteListener {
                ratingsSubject.onComplete()
            }
            .addOnCanceledListener {
                ratingsSubject.onError(Throwable("firebase set error"))
            }
        return Completable.fromObservable(ratingsSubject)
    }

    companion object {
        private const val RESULT_PATH = "ratings"
    }
}