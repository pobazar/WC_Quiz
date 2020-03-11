package msk.pobazar.wcquiz.core.navigation.transitionsParams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import msk.pobazar.wcquiz.domain.model.Question

@Parcelize
data class GameParams(
    val questions: List<Question>
): Parcelable