package msk.pobazar.wcquiz.repo.device

import msk.pobazar.wcquiz.model.AppVersion


interface AppInfoManager {
    val appVersion: AppVersion
}
