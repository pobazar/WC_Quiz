package msk.pobazar.wcquiz.data_remote.broadcast

import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse

interface QuestionsBroadcast {
    val questions: PublishSubject<List<QuestionResponse>>
}
