package msk.pobazar.wcquiz.di

import android.content.Context
import msk.pobazar.wcquiz.navigation.ScreenFactory
import msk.pobazar.wcquiz.navigation.ScreenFactoryImpl
import toothpick.config.Module

class AppModule(
    context: Context
) : Module() {
    init {
        bind(Context::class.java)
            .toInstance(context)

        bind(ScreenFactory::class.java)
            .to(ScreenFactoryImpl::class.java)
            .singleton()
    }
}