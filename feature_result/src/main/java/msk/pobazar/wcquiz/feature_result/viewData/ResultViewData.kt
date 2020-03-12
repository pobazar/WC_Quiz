package msk.pobazar.wcquiz.feature_result.viewData

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultViewData(
    val question: String,
    val answerRight: String,
    val image: Bitmap,
    val isRight: Boolean
) : Parcelable