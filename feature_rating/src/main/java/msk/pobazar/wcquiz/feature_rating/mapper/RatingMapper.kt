package msk.pobazar.wcquiz.feature_rating.mapper

import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewData
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewItem
import toothpick.InjectConstructor

@InjectConstructor
class RatingMapper {

    fun toViewData(ratings: Pair<List<Rating>, Rating>) =
        RatingViewData(
            items = ratings.first.map {
                RatingViewItem(
                    countRight = it.countRight.toString(),
                    score = it.score.toLong().toString(),
                    time = ((it.time / 100).toFloat() / 10).toString(),
                    name = it.name,
                    date = it.date.toString(),
                    userRating = false
                )
            }.toMutableList()
                .apply {
                    add(
                        with(ratings.second) {
                            RatingViewItem(
                                countRight = countRight.toString(),
                                score = score.toLong().toString(),
                                time = ((time / 100).toFloat() / 10).toString(),
                                name = name,
                                date = date.toString(),
                                userRating = true
                            )
                        }
                    )
                }
        )
}
