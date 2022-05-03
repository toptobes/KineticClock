package com.clock.dynamicmovements

import androidx.compose.ui.geometry.Offset
import com.clock.clock.ClockState
import com.clock.clock.Movement
import kotlin.math.atan2

object MouseCircle : Movement.Dynamic {

    override fun movement(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset): ClockState {
        val clockCenterX = clockCenter.x
        val clockCenterY = clockCenter.y
        val mouseX = pointerPosition.x + 6
        val mouseY = pointerPosition.y + 6

        val angle = atan2(
            y = mouseY - clockCenterY,
            x = mouseX - clockCenterX
        )

        return state.copy(
            hand1 = (angle * 180 / Math.PI).toFloat(),
            fused = true
        )
    }
}
