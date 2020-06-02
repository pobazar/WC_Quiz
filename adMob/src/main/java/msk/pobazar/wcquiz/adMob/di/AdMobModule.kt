package msk.pobazar.wcquiz.adMob.di

import android.content.Context
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAd
import msk.pobazar.wcquiz.core.BuildConfig
import toothpick.config.Module

class AdMobModule(
    context: Context
) : Module() {

    init {
        bind(InterstitialAd::class.java)
            .toInstance(
                InterstitialAd(context).apply {
                    adUnitId = if (BuildConfig.DEBUG) PAGE_KEY_TEST else PAGE_KEY
                }
            )

        bind(RewardedAd::class.java)
            .toInstance(
                RewardedAd(
                    context,
                    if (BuildConfig.DEBUG) REWARD_KEY_TEST else REWARD_KEY
                )
            )

    }

    companion object {
        private const val PAGE_KEY = "ca-app-pub-1267411731078735/8944406635"
        private const val PAGE_KEY_TEST = "ca-app-pub-3940256099942544/1033173712"

        private const val REWARD_KEY = "ca-app-pub-1267411731078735/6318243295"
        private const val REWARD_KEY_TEST = "ca-app-pub-3940256099942544/5224354917"
    }
}