package msk.pobazar.wcquiz.database_firebase.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.data_remote.api.QuestionApi
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import timber.log.Timber
import javax.inject.Inject

class QuestionApiImpl @Inject constructor(
    private val reference: DatabaseReference
) : QuestionApi {

    private val questionsSubject: PublishSubject<List<QuestionResponse>> = PublishSubject.create()

    override fun readAllQuestions(): PublishSubject<List<QuestionResponse>> {
        val database = reference
        val ref = database.child(QUESTIONS_PATH)

        val query: Query = ref.orderByChild(QUESTIONS_PATH)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<QuestionResponse>()
                for (singleSnapshot in dataSnapshot.children) {
                    val data = singleSnapshot.getValue(QuestionResponse::class.java)
                    data?.let { list.add(data) }
                    Timber.d("firebase: $data")
                }
                questionsSubject.onNext(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                questionsSubject.onError(Throwable(databaseError.toException()))
                Timber.e("onCancelled: ${databaseError.toException()}")
            }
        })
        return questionsSubject
    }

    override fun writeAll(data: List<QuestionResponse>) {
        reference.child(QUESTIONS_PATH).setValue(QUESTIONS_PATH)
    }

    companion object {
        private const val QUESTIONS_PATH = "questions"
    }
}