package msk.pobazar.wcquiz.feature_menu.dialog.presenter

import moxy.InjectViewState
import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.domain.interactor.UserInteractor
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import msk.pobazar.wcquiz.feature_menu.R
import javax.inject.Inject

@InjectViewState
class ChangeNameDialogPresenter @Inject constructor(
    private val userInteractor: UserInteractor,
    private val resourceManager: ResourceManager
) : BasePresenter<ChangeNameDialogView>() {

    private var name: String = ""

    override fun onFirstViewAttach() {
        name = userInteractor.getUser().name
        viewState.setCountSymbols(resourceManager.getString(R.string.count_symbols, name.length, COUNT_SYMBOLS))
        viewState.setName(name)
        super.onFirstViewAttach()
    }

    fun onPositiveClick() {
        if (validateName()) {
            userInteractor.updateName(name)
                .subscribe()
                .bind()
            viewState.closeDialog()
        }
    }

    fun onNameChanged(newName: String) {
        if (newName.length <= COUNT_SYMBOLS)
            name = newName
        else
            viewState.setName(name)
        viewState.setCountSymbols(resourceManager.getString(R.string.count_symbols, name.length, COUNT_SYMBOLS))
    }

    private fun validateName(): Boolean {
        //TODO валидация имени
        return true
    }

    companion object {
        private const val COUNT_SYMBOLS = 17
    }
}