package com.clock.dynamicmovements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import com.clock.clock.ClockState
import com.clock.clock.Movement
import kotlin.math.atan2

object MouseCircle : Movement.Dynamic, Movement {

    @Composable
    override fun movement(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset): ClockState {
        val angle = atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x)

        return state.copy(
            hand1 = mutableStateOf((angle * 180 / Math.PI).toFloat()),
            hand2 = mutableStateOf((angle * 180 / Math.PI).toFloat()),
        )
    }
}
