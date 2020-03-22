package msk.pobazar.wcquiz.data_remote.repo

import android.util.Log
import io.reactivex.Single
import msk.pobazar.wcquiz.data_remote.api.ImageApi
import msk.pobazar.wcquiz.data_remote.api.QuestionApi
import msk.pobazar.wcquiz.data_remote.mapper.QuestionApiMapper
import msk.pobazar.wcquiz.data_remote.models.QuestionResponse
import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.repo.remote.QuestionRepoRemote
import javax.inject.Inject

class QuestionRepoRemoteImpl @Inject constructor(
    private val questionApi: QuestionApi,
    private val imageApi: ImageApi,
    private val questionApiMapper: QuestionApiMapper
) : QuestionRepoRemote {

    override fun getAllQuestions(): Single<List<Question>> {
        lateinit var questions: List<QuestionResponse>
        return questionApi.readAllQuestions()
            .doOnNext { questions = it }
            .flatMapIterable {
                it
            }
            .flatMap {
                imageApi.getUri(it.picture.orEmpty())
            }
            .toList()
            .map {
                questionApiMapper.mapApiToQuestion(Pair(it, questions))
            }
    }
}
