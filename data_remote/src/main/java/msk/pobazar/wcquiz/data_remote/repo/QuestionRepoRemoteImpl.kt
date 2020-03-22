package msk.pobazar.wcquiz.data_remote.repo

import io.reactivex.Observable
import msk.pobazar.wcquiz.data_remote.api.QuestionApi
import msk.pobazar.wcquiz.data_remote.broadcast.QuestionsBroadcast
import msk.pobazar.wcquiz.data_remote.mapper.QuestionApiMapper
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.remote.QuestionRepoRemote
import javax.inject.Inject

class QuestionRepoRemoteImpl @Inject constructor(
    private val questionApi: QuestionApi,
    private val questionsBroadcast: QuestionsBroadcast,
    private val questionApiMapper: QuestionApiMapper
) : QuestionRepoRemote {

    private val questions: Observable<List<QuestionResponse>>
        get() = questionsBroadcast.questions

    override fun getAllQuestions(): Observable<List<Question>> {
        questionApi.readAllQuestions()
        return questions
            .map {
                it.map(questionApiMapper::mapApiToQuestion)
            }
    }
}