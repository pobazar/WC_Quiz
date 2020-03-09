package msk.pobazar.wcquiz.repo_device.repo

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import msk.pobazar.wcquiz.domain.repo.device.DeviceManager
import javax.inject.Inject

class DeviceManagerImpl @Inject constructor(
    private val context: Context
) : DeviceManager {

    override fun getSystemVersion(): String {
        return java.lang.Double.parseDouble(
            java.lang.String(Build.VERSION.RELEASE).replaceAll(
                "(\\d+[.]\\d+)(.*)",
                "$1"
            )
        ).toString()
    }

    override fun getAppVersion(): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }

    override fun isPhone(): Boolean {
        val w = context.resources.displayMetrics.widthPixels
        val h = context.resources.displayMetrics.heightPixels
        return if (w < h) {
            (w / Resources.getSystem().displayMetrics.density).toInt() <= 480
        } else {
            (h / Resources.getSystem().displayMetrics.density).toInt() <= 480
        }
    }

    override fun isLandscape(): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    override fun getScreenWidth(): Int {
        return context.resources.displayMetrics.widthPixels
    }

    override fun getScreenHeight(): Int {
        return context.resources.displayMetrics.heightPixels
    }

    override fun getAppBarHeight(): Int {
        val tv = TypedValue()
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }

        return 0
    }

    override fun getStatusBarHeight(): Int {
        val i = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (i > 0) {
            return context.resources.getDimensionPixelSize(i)
        }
        return 0
    }

    override fun getComplexBarsHeight(): Int = getAppBarHeight() + getStatusBarHeight()
}
