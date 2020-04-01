package msk.pobazar.wcquiz.core.navigation.transitionsParams

import msk.pobazar.wcquiz.domain.model.Theme

data class GameParams(
    val theme: Theme? = null
) : TransitionsParams