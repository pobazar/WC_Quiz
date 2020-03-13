package msk.pobazar.wcquiz.domain.repo.device

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

interface ResourceManager {
    fun getString(resId: Int, vararg params: Any?): String

    fun getDimension(resId: Int): Int

    fun getBoolean(resId: Int): Boolean

    fun getColor(id: Int): Int

    fun getList(id: Int): List<String>

    fun getQuantityString(pluralsId: Int, quantity: Int, resForZero: Int = 0): String

    fun getInteger(resId: Int): Int

    fun getFloat(resId: Int): Float

    fun getDrawableResIdByName(resName: String): Int

    fun getDrawable(resId: Int): Drawable

    fun getBitmap(resId: Int): Bitmap
}
