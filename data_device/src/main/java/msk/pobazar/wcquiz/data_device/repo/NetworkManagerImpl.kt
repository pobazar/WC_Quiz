package msk.pobazar.wcquiz.data_device.repo

import android.content.Context
import android.net.ConnectivityManager
import msk.pobazar.wcquiz.domain.repo.device.NetworkManager
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(
    private val context: Context
) : NetworkManager {

    override fun isNetworkAvailable(): Boolean {
        val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork = conManager?.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}