package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.model.Theme
import toothpick.InjectConstructor

@InjectConstructor
class ThemeInteractor {

    fun getAll(): List<Theme> {
        return Theme::class.java.enumConstants.toList()
    }
}