package msk.pobazar.wcquiz.adMob

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import javax.inject.Inject

class PageAds @Inject constructor(
    private val interstitialAd: InterstitialAd
) {
    fun create() {

        loadAds()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
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