package msk.pobazar.wcquiz.domain.model

enum class Theme(
    val title: String
) {
    LOR(
        title = "История"
    ),
    RAID(
        title = "Рейды и подземелья"
    ),
    PERSON(
        title = "Персонажи"
    ),
    AREA(
        title = "Локации"
    )
}