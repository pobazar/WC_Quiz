package msk.pobazar.wcquiz.view_error

enum class ErrorType(
    val imageRes: Int,
    val titleRes: Int,
    val subtitleRes: Int,
    val btnText: Int
) {

    NONE(
        imageRes = 0,
        titleRes = 0,
        subtitleRes = 0,
        btnText = 0
    ),

    ERROR_NETWORK_UNAVAILABLE(
        imageRes = R.drawable.android_ui_sign_error_noconneciton,
        titleRes = R.string.network_error,
        subtitleRes = R.string.network_error_description,
        btnText = R.string.retry
    ),

    ERROR_SERVER_UNAVAILABLE(
        imageRes = R.drawable.android_ui_sign_error,
        titleRes = R.string.server_error,
        subtitleRes = R.string.server_error_description,
        btnText = R.string.retry
    ),
}
