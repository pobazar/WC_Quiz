package msk.pobazar.wcquiz.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun View.visible(visible: Boolean, saveBounds: Boolean = false) {
    this.visibility = when {
        visible -> View.VISIBLE
        !visible && saveBounds -> View.INVISIBLE
        else -> View.GONE
    }
}

fun EditText.afterTextChanged(text: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            text.invoke(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // empty
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // empty
        }
    })
}

fun View.setOnClick(onClick: () -> Unit) {
    setOnClickListener { onClick() }
}

fun TextView.text(): ReadWriteProperty<Any, String> =
    object : ReadWriteProperty<Any, String> {
        override fun getValue(
            thisRef: Any,
            property: KProperty<*>
        ): String = text.toString()

        override fun setValue(
            thisRef: Any,
            property: KProperty<*>,
            value: String
        ) {
            text = value
        }
    }

fun View.enable(): ReadWriteProperty<Any, Boolean> =
    object : ReadWriteProperty<Any, Boolean> {
        override fun getValue(
            thisRef: Any,
            property: KProperty<*>
        ): Boolean = isEnabled

        override fun setValue(
            thisRef: Any,
            property: KProperty<*>,
            value: Boolean
        ) {
            isEnabled = value
        }
    }

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context)
        .inflate(layoutId, this, attachToRoot)
}