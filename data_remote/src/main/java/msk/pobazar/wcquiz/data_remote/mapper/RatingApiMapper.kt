package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.RatingResponse
import msk.pobazar.wcquiz.domain.model.Rating
import msk.pobazar.wcquiz.domain.orZero
import toothpick.InjectConstructor

@InjectConstructor
class RatingApiMapper {

    fun mapApiToRating(api: RatingResponse): Rating =
        Rating(
            countRight = api.countRight.orZero(),
            score = api.score.orZero(),
            time = api.time.orZero(),
            user = api.user.orEmpty()
        )

    fun mapRatingToApi(rating: Rating, countAll: Int, winStrick: Int, score: Float): RatingResponse =
        RatingResponse(
            countAll = countAll,
            countRight = rating.countRight,
            score = score,
            time = rating.time,
            user = rating.user,
            winStrick = winStrick
        )
}