package msk.pobazar.wcquiz

import android.app.Application
import msk.pobazar.wcquiz.data_device.di.DataDeviceModule
import msk.pobazar.wcquiz.data_local.di.DataLocalModule
import msk.pobazar.wcquiz.data_remote.di.DataRemoteModule
import msk.pobazar.wcquiz.di.AppModule
import msk.pobazar.wcquiz.domain.di.DependenciesInjector
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
    
    private fun initDI() {
        Toothpick.openScope(DependenciesInjector.APPLICATION_SCOPE)
                .apply {
                    installModules(
                            AppModule(this@App),
                            DataRemoteModule(),
                            DataLocalModule(),
                            DataDeviceModule()
                    )
                }
    }
}