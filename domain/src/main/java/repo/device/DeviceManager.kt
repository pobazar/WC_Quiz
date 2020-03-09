package repo.device

interface DeviceManager {

    fun getSystemVersion(): String

    fun getAppVersion(): String

    fun isPhone(): Boolean

    fun isLandscape(): Boolean

    fun getScreenWidth(): Int

    fun getScreenHeight(): Int

    fun getAppBarHeight(): Int

    fun getStatusBarHeight(): Int

    fun getComplexBarsHeight(): Int
}
