package msk.pobazar.wcquiz.domain.repo.local

import msk.pobazar.wcquiz.domain.model.Question
import msk.pobazar.wcquiz.domain.model.Theme

interface QuestionRepoLocal {

    fun getRandomQuestions(count: Int): List<Question>

    fun getRandomQuestions(count: Int, theme: Theme): List<Question>

    fun setAllQuestions(data: List<Question>)

    fun removeAllQuestions()
}