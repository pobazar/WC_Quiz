package msk.pobazar.wcquiz.feature_menu.dialog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_change_name.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.ui.BaseDialogFragment
import msk.pobazar.wcquiz.core.extensions.afterTextChanged
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.core.extensions.toEditable
import msk.pobazar.wcquiz.feature_menu.R
import msk.pobazar.wcquiz.feature_menu.dialog.presenter.ChangeNameDialogPresenter
import msk.pobazar.wcquiz.feature_menu.dialog.presenter.ChangeNameDialogView
import javax.inject.Inject

class ChangeNameDialogFragment : BaseDialogFragment(), ChangeNameDialogView {

    override val layout = R.layout.dialog_change_name

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: ChangeNameDialogPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false);
    }

    override fun initUi() {

    }

    override fun initUx() {
        etChangeNameInputName.afterTextChanged { presenter.onNameChanged(it) }
        tvChangeNameOk.setOnClick { presenter.onPositiveClick() }
        tvChangeNameCancel.setOnClick { dismiss() }
    }

    override fun setName(name: String) {
        etChangeNameInputName.text = name.toEditable()
    }

    override fun setCountSymbols(count: String) {
        tvChangeNameCountSymbols.text = count
    }

    override fun closeDialog() {
        dismiss()
    }

    companion object {
        fun newInstance() = ChangeNameDialogFragment()
    }
}