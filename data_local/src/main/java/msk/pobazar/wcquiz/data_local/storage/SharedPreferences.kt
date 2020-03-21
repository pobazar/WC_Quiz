package msk.pobazar.wcquiz.data_local.storage

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun SharedPreferences.string(
    defaultValue: String = "",
    key: (KProperty<*>) -> String = { it.name }
) = object : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        getString(key(property), defaultValue)

    override fun setValue(
        thisRef: Any,
        property: KProperty<*>,
        value: String
    ) = edit().putString(key(property), value).apply()
}

fun SharedPreferences.int(
    key: (KProperty<*>) -> String = { it.name }
) = object : ReadWriteProperty<Any, Int?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int? =
        if (contains(key(property))) {
            getInt(key(property), 0)
        } else null

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int?) = edit().run {
        if (value == null) remove(key(property)) else putInt(key(property), value)
    }.apply()
}

fun SharedPreferences.boolean(
    defaultValue: Boolean,
    key: (KProperty<*>) -> String = { it.name }
) = object : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        getBoolean(key(property), defaultValue)

    override fun setValue(
        thisRef: Any,
        property: KProperty<*>,
        value: Boolean
    ) = edit().putBoolean(key(property), value).apply()
}
