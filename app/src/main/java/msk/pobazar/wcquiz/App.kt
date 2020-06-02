package msk.pobazar.wcquiz

import android.app.Application
import com.google.android.gms.ads.MobileAds
import io.paperdb.Paper
import msk.pobazar.wcquiz.adMob.di.AdMobModule
import msk.pobazar.wcquiz.data_device.di.DataDeviceModule
import msk.pobazar.wcquiz.data_local.di.DataLocalModule
import msk.pobazar.wcquiz.data_remote.di.DataRemoteModule
import msk.pobazar.wcquiz.database_firebase.di.ApiModule
import msk.pobazar.wcquiz.di.AppModule
import msk.pobazar.wcquiz.domain.di.DependenciesInjector
import msk.pobazar.wcquiz.navigation.di.NavigationModule
import msk.pobazar.wcquiz.storage_paper.di.StorageModule
import timber.log.Timber
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initAdMob()
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

    private fun initAdMob() {
        MobileAds.initialize(this@App)
    }

    private fun initDI() {
        Toothpick.openScope(DependenciesInjector.APPLICATION_SCOPE)
            .apply {
                installModules(
                    AppModule(this@App),
                    DataRemoteModule(),
                    DataLocalModule(this@App),
                    DataDeviceModule(),
                    NavigationModule(),
                    StorageModule(),
                    AdMobModule(this@App),
                    ApiModule()
                )
            }
    }
}