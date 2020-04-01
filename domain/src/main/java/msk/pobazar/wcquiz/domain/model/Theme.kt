package msk.pobazar.wcquiz.domain.model

enum class Theme(
    val code: Int,
    val title: String
) {
    LOR(
        code = 0,
        title = "История"
    ),
    RAID(
        code = 1,
        title = "Рейды и подземелья"
    ),
    PERSON(
        code = 2,
        title = "Персонажи"
    ),
    AREA(
        code = 3,
        title = "Локации"
    )
}