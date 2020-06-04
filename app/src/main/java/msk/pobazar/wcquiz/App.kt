package msk.pobazar.wcquiz

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
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

    private val app = this@App
    override fun onCreate() {
        super.onCreate()
        initAppMetrica()
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
        MobileAds.initialize(app)
    }

    private fun initAppMetrica() {
        if (!BuildConfig.DEBUG) {
            YandexMetrica.activate(
                applicationContext,
                YandexMetricaConfig
                    .newConfigBuilder(METRICA_KEY)
                    .build()
            )
            YandexMetrica.enableActivityAutoTracking(this)
        }
    }

    private fun initDI() {
        Toothpick.openScope(DependenciesInjector.APPLICATION_SCOPE)
            .apply {
                installModules(
                    AppModule(app),
                    DataRemoteModule(),
                    DataLocalModule(app),
                    DataDeviceModule(),
                    NavigationModule(),
                    StorageModule(),
                    AdMobModule(app),
                    ApiModule()
                )
            }
    }

    companion object {
        private const val METRICA_KEY = "408213b0-7b87-497b-8771-56aa650a3f65"
    }
}