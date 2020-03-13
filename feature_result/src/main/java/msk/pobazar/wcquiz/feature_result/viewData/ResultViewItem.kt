package msk.pobazar.wcquiz.feature_result.viewData

import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem

class ResultViewItem(
    val question: String,
    val answerRight: String,
    val image: String,
    val color: Int,
    val showAnswer: Boolean
): ViewItem