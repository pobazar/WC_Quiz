package msk.pobazar.wcquiz.repo

import msk.pobazar.wcquiz.model.AppVersion
import msk.pobazar.wcquiz.repo_device.BuildConfig
import repo.device.AppInfoManager
import javax.inject.Inject

class AppInfoManagerImpl @Inject constructor() : AppInfoManager {

    override val appVersion: AppVersion
        get() = AppVersion(
            versionName = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE,
            debug = BuildConfig.DEBUG
        )
}
