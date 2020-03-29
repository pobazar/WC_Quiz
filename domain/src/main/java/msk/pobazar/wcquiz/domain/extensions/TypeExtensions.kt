package msk.pobazar.wcquiz.domain.extensions

import java.util.Date

fun Long?.orZero() = this ?: 0

fun Long?.orOne() = this ?: 1

fun Long?.orEmpty() = this?.toString() ?: ""

fun Int?.orZero() = this ?: 0

fun Any?.orValue(value: Int) = this ?: value

fun Double?.orZero() = this ?: 0.0

fun Float?.orZero() = this ?: 0f

fun Float?.orOne() = this ?: 1f

fun Date?.orEmpty() = this ?: Date(0)

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Date?.orZero() = this ?: Date(0)
