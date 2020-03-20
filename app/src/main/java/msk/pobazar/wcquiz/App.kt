package msk.pobazar.wcquiz

import android.app.Application
import io.paperdb.Paper
import msk.pobazar.wcquiz.data_device.di.DataDeviceModule
import msk.pobazar.wcquiz.data_local.di.DataLocalModule
import msk.pobazar.wcquiz.data_remote.di.DataRemoteModule
import msk.pobazar.wcquiz.di.AppModule
import msk.pobazar.wcquiz.domain.di.DependenciesInjector
import msk.pobazar.wcquiz.navigation.di.NavigationModule
import msk.pobazar.wcquiz.storage.di.StorageModule
import timber.log.Timber
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogger()
        initPaper()
        initDI()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initPaper() {
        Paper.init(this)
    }

    private fun initDI() {
        Toothpick.openScope(DependenciesInjector.APPLICATION_SCOPE)
            .apply {
                installModules(
                    AppModule(this@App),
                    DataRemoteModule(),
                    DataLocalModule(),
                    DataDeviceModule(),
                    NavigationModule(),
                    StorageModule()
                )
            }
    }
}