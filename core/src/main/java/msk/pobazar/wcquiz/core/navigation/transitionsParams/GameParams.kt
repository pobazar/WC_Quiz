package msk.pobazar.wcquiz.core.navigation.transitionsParams

import msk.pobazar.wcquiz.domain.model.Theme
import java.io.Serializable

data class GameParams(
    val theme: Theme? = null
) : Serializable