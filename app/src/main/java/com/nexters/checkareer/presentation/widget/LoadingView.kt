package com.nexters.checkareer.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.lottie.LottieAnimationView
import com.nexters.checkareer.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val lottieAnimationView: LottieAnimationView

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_animation_layout, this, true)
        lottieAnimationView = findViewById(R.id.lottie_animation)
    }

    private fun playLottie() {
        if (!lottieAnimationView.isAnimating) {
            lottieAnimationView.playAnimation()
        }
    }

    private fun stopLottie() {
        lottieAnimationView.cancelAnimation()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == VISIBLE) playLottie() else stopLottie()
    }
}