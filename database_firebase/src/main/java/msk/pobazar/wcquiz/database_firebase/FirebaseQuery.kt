package msk.pobazar.wcquiz.database_firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import toothpick.InjectConstructor

@InjectConstructor
class FirebaseQuery {

    internal inline fun <reified T> read(query: Query): PublishSubject<List<T>> {
        val subject: PublishSubject<List<T>> = PublishSubject.create()

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<T>()
                for (singleSnapshot in dataSnapshot.children) {
                    val data = singleSnapshot.getValue(T::class.java)
                    data?.let { list.add(data) }
                    Timber.d("firebase: $data")
                }
                subject.onNext(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                subject.onError(Throwable(databaseError.toException()))
                Timber.e("onCancelled: ${databaseError.toException()}")
            }
        })
        return subject
    }

    internal fun write(query: Task<Void>): Completable {
        val ratingsSubject: PublishSubject<Completable> = PublishSubject.create()

        query
            .addOnCompleteListener {
                ratingsSubject.onComplete()
                Timber.d("firebase set error")
            }
            .addOnCanceledListener {
                ratingsSubject.onError(Throwable("firebase set error"))
                Timber.d("firebase set error")
            }
        return Completable.fromObservable(ratingsSubject)
    }
}