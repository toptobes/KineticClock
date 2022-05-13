package com.clock.dynamicpatterns

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import com.clock.clock.Pattern
import com.clock.clock.ClockState
import com.clock.clock.toDeg
import com.clock.clock.toRad
import com.clock.szttings.Settings.asm
import kotlin.math.atan2

object Fragmented : Pattern.Dynamic, Pattern {

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
            mutableStateOf(atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x) + 92f)
        }

        val angle by animateFloatAsState(
            if (started) state.hand1.toRad() else target,
            tween(durationMillis = 120.asm, easing = LinearEasing),
            finishedListener = {
                done = true
            }
        )

        state.hand1 = angle.toDeg()
        state.hand2 = state.hand1 - 180f

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

        val angle = atan2(pointerPosition.y - clockCenter.y, pointerPosition.x - clockCenter.x) + 92f

        state.hand1 = angle.toDeg()
        state.hand2 = state.hand1 - 180f
    }
}
