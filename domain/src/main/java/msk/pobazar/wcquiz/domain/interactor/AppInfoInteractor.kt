package msk.pobazar.wcquiz.domain.interactor

import msk.pobazar.wcquiz.domain.repo.device.AppInfoManager
import javax.inject.Inject

class AppInfoInteractor @Inject constructor(
    appInfoManager: AppInfoManager
) {

    val version = appInfoManager.appVersion
}
