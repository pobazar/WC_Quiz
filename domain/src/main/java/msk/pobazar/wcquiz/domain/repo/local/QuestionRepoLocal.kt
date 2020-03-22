package msk.pobazar.wcquiz.domain.repo.local

import msk.pobazar.wcquiz.domain.model.Question

interface QuestionRepoLocal {

    fun getRandomQuestions(count: Int): List<Question>

    fun setAllQuestions(questionRespons: List<Question>)

    fun removeAllQuestions()
}