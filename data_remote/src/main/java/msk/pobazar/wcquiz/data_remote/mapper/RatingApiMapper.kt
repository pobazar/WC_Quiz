package msk.pobazar.wcquiz.data_remote.mapper

import msk.pobazar.wcquiz.data_remote.models.RatingResponse
import msk.pobazar.wcquiz.domain.model.Rating
import toothpick.InjectConstructor

@InjectConstructor
class RatingApiMapper {

    fun toDomain(api: RatingResponse): Rating =
        with(api) {
            Rating(
                countRight = countRight,
                countAll = countAll,
                score = score,
                time = time,
                name = name,
                date = date
            )
        }

    fun toApi(rating: Rating, winStrick: Int): RatingResponse =
        with(rating) {
            RatingResponse(
                countAll = countAll,
                countRight = countRight,
                score = score,
                time = time,
                name = name,
                winStrick = winStrick,
                date = date
            )
        }
}