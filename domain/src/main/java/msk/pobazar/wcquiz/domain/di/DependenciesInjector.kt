package msk.pobazar.wcquiz.domain.di

import toothpick.Toothpick
import toothpick.config.Module

class DependenciesInjector {

    fun <V> openScope(
        target: V,
        scopes: List<Any?>,
        closeAfterInject: Boolean = false,
        moduleProvider: (Module) -> Unit = {}
    ) {

        val opened = Toothpick.openScopes(APPLICATION_SCOPE, *scopes.toTypedArray())
        opened.installModules(object : Module() {
            init {
                moduleProvider(this)
            }
        })
        Toothpick.inject(target, opened)

        if (closeAfterInject) {
            scopes.forEach {
                Toothpick.closeScope(it)
            }
        }
    }

    fun <V> closeScope(scope: V) {
        Toothpick.closeScope(scope)
    }

    companion object {
        const val APPLICATION_SCOPE = "application_scope"
    }
}
