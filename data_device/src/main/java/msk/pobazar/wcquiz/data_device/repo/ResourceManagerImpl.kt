package msk.pobazar.wcquiz.data_device.repo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import msk.pobazar.wcquiz.domain.repo.device.ResourceManager
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(resId: Int, vararg params: Any?): String = runCatching {
        context.getString(resId, *params)
    }.fold(
        onSuccess = { it },
        onFailure = { "" }
    )

    override fun getDimension(resId: Int): Int = context.resources.getDimensionPixelSize(resId)

    override fun getBoolean(resId: Int): Boolean = context.resources.getBoolean(resId)

    override fun getColor(id: Int): Int = ContextCompat.getColor(context, id)

    override fun getList(id: Int): List<String> = context.resources.getStringArray(id).toList()

    override fun getQuantityString(pluralsId: Int, quantity: Int, resForZero: Int): String {
        return when {
            quantity == 0 && resForZero != 0 -> context.getString(resForZero)
            else -> context.resources.getQuantityString(pluralsId, quantity, quantity)
        }
    }

    override fun getInteger(resId: Int): Int = context.resources.getInteger(resId)

    override fun getFloat(resId: Int): Float {
        val typedValue = TypedValue()
        context.resources.getValue(resId, typedValue, true)
        return typedValue.float
    }

    override fun getDrawableResIdByName(resName: String): Int =
        context.resources.getIdentifier(resName, DRAWABLE, context.packageName)

    override fun getDrawable(resId: Int): Drawable {
        return ContextCompat.getDrawable(context, resId)
            ?: ColorDrawable(Color.TRANSPARENT)
    }

    override fun getBitmap(resId: Int): Bitmap = BitmapFactory.decodeResource(context.resources, resId)

    companion object {
        private const val DRAWABLE = "drawable"
    }
}
