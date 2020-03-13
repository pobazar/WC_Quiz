package msk.pobazar.wcquiz.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import msk.pobazar.wcquiz.domain.di.DependenciesInjector
import toothpick.config.Module

abstract class BaseFragment : MvpAppCompatFragment() {

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

    override fun onDestroy() {
        super.onDestroy()
        injector.closeScope(this)
    }

    protected abstract fun initUi()

    protected abstract fun initUx()

}