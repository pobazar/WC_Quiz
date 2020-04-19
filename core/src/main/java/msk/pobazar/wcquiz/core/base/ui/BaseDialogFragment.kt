package msk.pobazar.wcquiz.core.base.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import moxy.MvpAppCompatDialogFragment
import msk.pobazar.wcquiz.domain.di.DependenciesInjector
import toothpick.config.Module

abstract class BaseDialogFragment : MvpAppCompatDialogFragment() {

    protected abstract val layout: Int

    open val moduleProvider: (Module) -> Unit = {}

    private val injector = DependenciesInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.openScope(
            target = this,
            scopes = listOf(context, this),
            moduleProvider = moduleProvider
        )
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initUx()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog.apply {
            window?.requestFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        injector.closeScope(this)
    }

    protected abstract fun initUi()

    protected abstract fun initUx()
}
