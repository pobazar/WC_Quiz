package msk.pobazar.wcquiz.feature_rating.mapper

import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewData
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewItem
import toothpick.InjectConstructor

@InjectConstructor
class RatingMapper {

    fun toViewData(ratings: List<Rating>) =
        RatingViewData(
            items = ratings.map {
                RatingViewItem(
                    countRight = it.countRight.toString(),
                    score = it.score.toLong().toString(),
                    time = ((it.time / 100).toFloat() / 10).toString(),
                    user = it.user,
                    date = it.date.toString()
                )
            }
        )
}
