package com.clock.dynamicpattern

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import com.clock.clock.Pattern
import com.clock.clock.ClockState
import com.clock.clock.toRad
import kotlin.math.atan2

object Follow : Pattern.Dynamic, Pattern {

    @Composable
    override fun setUp(
        r: Int, c: Int,
        state: ClockState,
        clockCenter: Offset,
        pointerPosition: Offset
    ): Boolean {
        var done by remember { mutableStateOf(false) }
        if (done) return true

        var started by remember { mutableStateOf(true) }
        val target by remember {
            mutableStateOf(atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x))
        }

        val angle by animateFloatAsState(
            if (started) state.hand1.value.toRad() else atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x),
            tween(durationMillis = 120, easing = LinearEasing),
            finishedListener = {
                done = true
            }
        )

        state.hand1.value = (angle * 180f / Math.PI).toFloat() + 90f
        state.hand2.value = state.hand1.value - 180f

        started = false
        return false
    }

    @Composable
    override fun start(
        r: Int, c: Int,
        state: ClockState,
        clockCenter: Offset,
        pointerPosition: Offset
    ) {
        var done by remember { mutableStateOf(false) }
        if (!done) {
            done = setUp(r, c, state, clockCenter, pointerPosition)
            return
        }

        val angle = atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x)

        state.hand1.value = (angle * 180 / Math.PI).toFloat() + 90f
        state.hand2.value = state.hand1.value - 180f
    }
}