package msk.pobazar.wcquiz.domain.repo.device

import msk.pobazar.wcquiz.domain.model.AppVersion


interface AppInfoManager {
    val appVersion: AppVersion
}
