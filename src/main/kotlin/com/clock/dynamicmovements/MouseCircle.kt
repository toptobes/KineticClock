package com.clock.dynamicmovements

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import com.clock.clock.Action
import com.clock.clock.ClockState
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.atan2

object MouseCircle : Action.Dynamic, Action {

    @Composable
    override fun setUp(
        r: Int, c: Int,
        state: ClockState,
        clockCenter: Offset,
        pointerPosition: Offset
    ): Boolean {
        val setUp = remember { Animatable(0f) }
        val cs = rememberCoroutineScope()

        if (abs(setUp.value - atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x)) < 0.1f)
            return true

        cs.launch {
            setUp.animateTo(atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x))
        }

        state.hand2.value = setUp.value
        state.hand2.value = state.hand1.value

        return false
    }

    @Composable
    override fun start(
        r: Int, c: Int,
        state: ClockState,
        clockCenter: Offset,
        pointerPosition: Offset
    ) {
        if (!setUp(r, c, state, clockCenter, pointerPosition))
            return

        val angle = atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x)

        state.hand1.value = (angle * 180 / Math.PI).toFloat()
        state.hand2.value = state.hand1.value
    }
}

