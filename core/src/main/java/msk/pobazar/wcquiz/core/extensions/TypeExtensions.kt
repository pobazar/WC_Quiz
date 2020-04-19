package msk.pobazar.wcquiz.core.extensions

import android.text.Editable

fun String.toEditable() = Editable.Factory.getInstance().newEditable(this)