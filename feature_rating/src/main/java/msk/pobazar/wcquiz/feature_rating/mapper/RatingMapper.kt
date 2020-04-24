package msk.pobazar.wcquiz.feature_rating.mapper

import msk.pobazar.wcquiz.core.extensions.DATE_FORMAT_DD_MM_YY_HH_MM
import msk.pobazar.wcquiz.core.extensions.formatDateToString
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewData
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewItem
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewTitle
import toothpick.InjectConstructor

@InjectConstructor
class RatingMapper {

    fun toViewData(ratings: Pair<List<Rating>, Rating>): RatingViewData =
        RatingViewData(
            title = RatingViewTitle(),
            items = ratings.first
                .toMutableList()
                .run {
                    remove(ratings.second)
                    map {
                        toViewData(it, false)
                    }
                }
                .toMutableList()
                .apply {
                    add(
                        toViewData(ratings.second, true)
                    )
                }
                .filter { it.score != "0" }
                .sortedByDescending { it.score.toFloat() }
        )

    private fun toViewData(rating: Rating, isUser: Boolean) =
        with(rating) {
            RatingViewItem(
                countRight = countRight.toString(),
                score = score.toLong().toString(),
                time = ((time / 100).toFloat() / 10).toString(),
                name = name,
                date = date.formatDateToString(DATE_FORMAT_DD_MM_YY_HH_MM),
                userRating = isUser
            )
        }
}
