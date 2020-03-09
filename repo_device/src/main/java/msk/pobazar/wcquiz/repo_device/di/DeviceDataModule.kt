package msk.pobazar.wcquiz.repo_device.di

import msk.pobazar.wcquiz.repo_device.repo.AppInfoManagerImpl
import msk.pobazar.wcquiz.repo_device.repo.DeviceManagerImpl
import msk.pobazar.wcquiz.repo_device.repo.ResourceManagerImpl
import msk.pobazar.wcquiz.domain.repo.device.AppInfoManager
import msk.pobazar.wcquiz.domain.repo.device.DeviceManager
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import toothpick.config.Module

class DeviceDataModule : Module() {
    init {
        bind(AppInfoManager::class.java)
                .to(AppInfoManagerImpl::class.java)
                .singleton()
        
        bind(DeviceManager::class.java)
                .to(DeviceManagerImpl::class.java)
                .singleton()
        
        bind(ResourceManager::class.java)
                .to(ResourceManagerImpl::class.java)
                .singleton()
    }
}