package msk.pobazar.wcquiz.feature_result.viewData

import android.graphics.drawable.Drawable
import msk.pobazar.wcquiz.core.base.adapter.ViewItem

class ResultViewItem(
    val question: String,
    val answerRight: String,
    val image: String,
    val background: Drawable,
    val showAnswer: Boolean
) : ViewItem