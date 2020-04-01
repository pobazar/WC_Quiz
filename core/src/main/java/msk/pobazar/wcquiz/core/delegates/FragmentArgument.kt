package msk.pobazar.wcquiz.core.delegates

import android.os.Binder
import android.os.Bundle
import androidx.core.app.BundleCompat
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class FragmentArgument<T : Any> : ReadWriteProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return thisRef.arguments?.get(property.name) as? T
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        if (thisRef.arguments == null) thisRef.arguments = Bundle()

        val args = thisRef.arguments
        val key = property.name

        when (value) {
            is String -> args?.putString(key, value)
            is Int -> args?.putInt(key, value)
            is Short -> args?.putShort(key, value)
            is Long -> args?.putLong(key, value)
            is Byte -> args?.putByte(key, value)
            is ByteArray -> args?.putByteArray(key, value)
            is Char -> args?.putChar(key, value)
            is CharArray -> args?.putCharArray(key, value)
            is CharSequence -> args?.putCharSequence(key, value)
            is Float -> args?.putFloat(key, value)
            is Bundle -> args?.putBundle(key, value)
            is Binder -> BundleCompat.putBinder(args!!, key, value)
            is android.os.Parcelable -> args?.putParcelable(key, value)
            is java.io.Serializable -> args?.putSerializable(key, value)
            else -> throw IllegalStateException("Type ${value.javaClass.canonicalName} of property ${property.name} is not supported")
        }
    }
}
