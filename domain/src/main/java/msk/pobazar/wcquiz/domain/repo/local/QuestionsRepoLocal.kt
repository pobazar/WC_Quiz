package msk.pobazar.wcquiz.domain.repo.local

import msk.pobazar.wcquiz.domain.model.Question

interface QuestionsRepoLocal {

    fun getRandomQuestions(count: Int): List<Question>

    fun setAllQuestions(questions: List<Question>)

    fun removeAllQuestions()
}