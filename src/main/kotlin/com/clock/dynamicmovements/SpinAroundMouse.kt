package com.clock.dynamicmovements

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import com.clock.clock.ClockState
import com.clock.clock.Movement
import kotlin.math.sqrt

object SpinAroundMouse : Movement.Dynamic, Movement {

    @Composable
    override fun movement(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset): ClockState {
        val xDistance = pointerPosition.x - clockCenter.x
        val yDistance = pointerPosition.y - clockCenter.y
        val distance = sqrt((xDistance * xDistance) + (yDistance * yDistance))

        val rotationAnimation = animateFloatAsState(
            if (distance < 120) 0f else 360f,
            if (distance < 120) infiniteRepeatable(
                animation = tween(20, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ) else spring()
        )

        return ClockState(
            hand1 = rotationAnimation,
            hand2 = rotationAnimation,
        )
    }
}