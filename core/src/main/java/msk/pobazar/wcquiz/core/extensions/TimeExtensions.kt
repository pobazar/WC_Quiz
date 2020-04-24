package msk.pobazar.wcquiz.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val DATE_FORMAT_D_MMMM = "d MMMM"
const val DATE_FORMAT_D_MMMM_YYYY = "d MMMM yyyy"
const val DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
const val DATE_FORMAT_ANALYTICS = "yyyy-MM-dd HH:mm:ss.SSSZ"
const val DATE_FORMAT_DD_MM_YYYY = "dd.MM.yyyy"
const val DATE_FORMAT_DD_MM_YY = "dd.MM.yy"
const val DATE_FORMAT_DD_MM_YY_HH_MM = "dd.MM.yy HH:mm"
const val DATE_FORMAT_DD_MMMM_HH_MM = "dd MMMM, HH:mm"
const val DATE_FORMAT_DD_MMMM_HH_MM_SS = "dd MMMM, HH:mm:ss"
const val DATE_FORMAT_HH_MM = "HH:mm"
const val DATE_FORMAT_D = "D"
const val DATE_FORMAT_H = "k"
const val DATE_FORMAT_MM_SS = "mm:ss"

const val ONE_SECOND_IN_MS = 1000L
const val ONE_MINUTE_IN_MS = 60000L
const val ONE_HOUR_IN_MS = 3600000L
const val ONE_DAY_IN_MS = 86400000L

const val SECOND_IN_MILLIS = 1000L
const val MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60
const val HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60
const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
const val WEEK_IN_MILLIS = DAY_IN_MILLIS * 7


fun Date.formatDateToString(format: String): String =
    SimpleDateFormat(format, Locale.getDefault()).format(this)

fun Long.formatDateToString(format: String): String =
    SimpleDateFormat(format, Locale.getDefault()).format(this)
