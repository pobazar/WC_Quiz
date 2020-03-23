package msk.pobazar.wcquiz.view_loader

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import msk.pobazar.wcquiz.core.extensions.inflate

class LoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {



    init {

        inflate(
            layoutId = R.layout.view_load_holder,
            attachToRoot = true
        )
        initViewParams()
    }

    private fun initViewParams() {

        isFocusable = true
        isClickable = true
        setBackgroundResource(android.R.color.white)
    }
}
