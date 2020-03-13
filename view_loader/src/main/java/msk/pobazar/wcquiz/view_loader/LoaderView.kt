package msk.pobazar.wcquiz.view_loader

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import msk.pobazar.wcquiz.core.extensions.hideKeyboard
import msk.pobazar.wcquiz.core.extensions.inflate
import msk.pobazar.wcquiz.core.extensions.visible

class LoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val animationDuration by lazy {
        resources.getInteger(R.integer.loader_visible_animation_duration).toLong()
    }

    init {
        ContextCompat.getColor(context, R.color.loader_background)
            .let(::setBackgroundColor)

        isClickable = true

        inflate(R.layout.view_load_holder)

        show(false)
    }

    fun show(isShow: Boolean) {
        clearAnimation()
        animate()
            .alpha(if (isShow) 1f else 0f)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                    // not handled
                }
                override fun onAnimationCancel(animation: Animator?) {
                    // not handled
                }
                override fun onAnimationStart(animation: Animator?) {
                    if (isShow) visible(true)
                }
                override fun onAnimationEnd(animation: Animator?) {
                    if (!isShow) visible(false)
                }
            })
            .setDuration(animationDuration)
            .start()

        if (isShow) hideKeyboard()
    }
}