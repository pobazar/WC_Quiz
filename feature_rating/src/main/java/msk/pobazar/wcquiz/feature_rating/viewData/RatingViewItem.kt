package msk.pobazar.wcquiz.feature_rating.viewData

import msk.pobazar.wcquiz.core.base.adapter.ViewItem

class RatingViewItem(
    val rightPercent: String,
    val score: String,
    val time: String,
    val name: String,
    val date: String,
    val isUserRating: Boolean
) : ViewItem