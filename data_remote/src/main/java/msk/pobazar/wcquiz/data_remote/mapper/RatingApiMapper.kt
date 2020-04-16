package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.RatingResponse
import msk.pobazar.wcquiz.domain.model.Rating
import toothpick.InjectConstructor

@InjectConstructor
class RatingApiMapper {

    fun toDomain(api: RatingResponse): Rating =
        Rating(
            countRight = api.countRight,
            score = api.score,
            time = api.time,
            name = api.name,
            date = api.date
        )

    fun toApi(rating: Rating, countAll: Int, winStrick: Int): RatingResponse =
        RatingResponse(
            countAll = countAll,
            countRight = rating.countRight,
            score = rating.score,
            time = rating.time,
            name = rating.name,
            winStrick = winStrick,
            date = rating.date
        )
}