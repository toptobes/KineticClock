package com.clock.dynamicmovements

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import com.clock.clock.Action
import com.clock.clock.ClockState
import kotlin.math.atan2

object MouseCircle : Action.Dynamic, Action {

    @Composable
    override fun start(
        r: Int, c: Int,
        state: ClockState,
        clockCenter: Offset,
        pointerPosition: Offset
    ): ClockState {
        val angle = atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x)

        return state.apply {
            hand1.value = (angle * 180 / Math.PI).toFloat()
            hand2.value = (angle * 180 / Math.PI).toFloat()
        }
    }
}

