package msk.pobazar.wcquiz.view_error

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_error_typed.view.*
import msk.pobazar.wcquiz.core.extensions.inflate
import msk.pobazar.wcquiz.core.extensions.onAttrs
import msk.pobazar.wcquiz.core.extensions.setOnClick

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        inflate(R.layout.view_error_typed)

        initViewParams()
    }

    var type: ErrorType = ErrorType.ERROR_SERVER_UNAVAILABLE
        set(value) {
            field = value
            ivPicture.setImageResource(type.imageRes)
            tvTitle.setText(type.titleRes)
            tvSubtitle.setText(type.subtitleRes)
            btnAction.setText(type.btnText)
        }

    fun onActionClick(action: () -> Unit) {
        btnAction.setOnClick(action)
    }

    private fun initViewParams() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        isFocusable = true
        isClickable = true
        setBackgroundResource(android.R.color.white)
    }
}
