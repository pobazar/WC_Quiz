package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.RatingResponse
import msk.pobazar.wcquiz.domain.extensions.orZero
import msk.pobazar.wcquiz.domain.model.Rating
import toothpick.InjectConstructor

@InjectConstructor
class RatingApiMapper {

    fun toDomain(api: RatingResponse): Rating =
        Rating(
            countRight = api.countRight.orZero(),
            score = api.score.orZero(),
            time = api.time.orZero(),
            user = api.user.orEmpty(),
            date = api.date.orZero()
        )

    fun toApi(rating: Rating, countAll: Int, winStrick: Int): RatingResponse =
        RatingResponse(
            countAll = countAll,
            countRight = rating.countRight,
            score = rating.score,
            time = rating.time,
            user = rating.user,
            winStrick = winStrick,
            date = rating.date
        )
}