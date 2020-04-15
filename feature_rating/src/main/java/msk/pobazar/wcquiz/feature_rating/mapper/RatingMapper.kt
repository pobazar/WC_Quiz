package msk.pobazar.wcquiz.feature_rating.mapper

import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewData
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewItem
import toothpick.InjectConstructor

@InjectConstructor
class RatingMapper {

    fun toViewData(ratings: Pair<List<Rating>, Rating>): RatingViewData =
        RatingViewData(
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
                .sortedByDescending { it.score.toFloat() }
        )

    private fun toViewData(rating: Rating, isUser: Boolean) =
        with(rating) {
            RatingViewItem(
                countRight = countRight.toString(),
                score = score.toLong().toString(),
                time = ((time / 100).toFloat() / 10).toString(),
                name = name,
                date = date.toString(),
                userRating = isUser
            )
        }
}
