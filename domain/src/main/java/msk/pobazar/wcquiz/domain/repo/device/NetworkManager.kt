package msk.pobazar.wcquiz.domain.repo.device

interface NetworkManager {
    fun isAvailable(): Boolean
}