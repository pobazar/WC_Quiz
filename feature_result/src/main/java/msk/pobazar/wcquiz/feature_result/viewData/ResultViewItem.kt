package msk.pobazar.wcquiz.feature_result.viewData

import android.graphics.Bitmap
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem

class ResultViewItem(
    val question: String,
    val answerRight: String,
    val image: Bitmap,
    val color: Int,
    val showAnswer: Boolean
): ViewItem