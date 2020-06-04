package msk.pobazar.wcquiz.adMob

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import msk.pobazar.wcquiz.core.analytics.AnalyticsKeys
import msk.pobazar.wcquiz.domain.interactor.AnalyticsInteractor
import javax.inject.Inject

class PageAds @Inject constructor(
    private val interstitialAd: InterstitialAd,
    private val analyticsInteractor: AnalyticsInteractor
) {
    fun create() {

        loadAds()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                analyticsInteractor.reportEvent(AnalyticsKeys.BANNER_ADS, "{\"banner_ads\":\"load\"}")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                analyticsInteractor.reportEvent(AnalyticsKeys.BANNER_ADS, "{\"banner_ads\":\"$errorCode\"}")
            }

            override fun onAdOpened() {
                analyticsInteractor.reportEvent(AnalyticsKeys.BANNER_ADS, "{\"banner_ads\":\"open\"}")
            }

            override fun onAdClicked() {
                analyticsInteractor.reportEvent(AnalyticsKeys.BANNER_ADS, "{\"banner_ads\":\"click\"}")
            }

            override fun onAdClosed() {
                analyticsInteractor.reportEvent(AnalyticsKeys.BANNER_ADS, "{\"banner_ads\":\"close\"}")
                loadAds()
            }
        }
    }

    fun loadAds() {
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    fun show() {
        if (interstitialAd.isLoaded) {
            interstitialAd.show()
        } else {
            if (!interstitialAd.isLoading)
                loadAds()
        }
    }

    companion object {
        const val onAdLoaded = 1
        const val onAdFailedToLoad = 2
        const val onAdOpened = 3
        const val onAdClicked = 4
        const val onAdLeftApplication = 5
        const val onAdClosed = 6
    }
}