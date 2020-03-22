package msk.pobazar.wcquiz.database_firebase.broadcast

import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.data_remote.broadcast.QuestionsBroadcast
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.model.Question
import toothpick.InjectConstructor

@InjectConstructor
class QuestionsBroadcastImpl : QuestionsBroadcast {
    override val questions: PublishSubject<List<QuestionResponse>> = PublishSubject.create()
}