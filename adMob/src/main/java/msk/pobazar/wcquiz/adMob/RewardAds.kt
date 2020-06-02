package msk.pobazar.wcquiz.adMob

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import javax.inject.Inject

class RewardAds @Inject constructor(
    private val rewardedAd: RewardedAd
) {

    private val adLoadCallback = object : RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            // Ad successfully loaded.
        }

        override fun onRewardedAdFailedToLoad(errorCode: Int) {
            // Ad failed to load.
        }
    }

    fun show(activity: Activity, adCallback: RewardedAdCallback) {
        if (rewardedAd.isLoaded)
            rewardedAd.show(activity, adCallback)
        else {
            loadAds()
            Toast.makeText(activity, "Реклама еще не загрузилась", Toast.LENGTH_SHORT).show()
        }
    }

    fun loadAds() {
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
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