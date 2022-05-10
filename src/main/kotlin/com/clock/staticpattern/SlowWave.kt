package com.clock.staticpattern

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import com.clock.clock.ClockState
import com.clock.clock.Pattern
import kotlin.math.abs

object SlowWave : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        var done by remember { mutableStateOf(false) }
        if (done) return true

        state.animateTo(45f, 225f, tween(1000, easing = LinearEasing))

        if (abs(state.hand1.value - 45f) < 0.1f && abs(state.hand2.value - 225f) < 0.1f) {
            done = true
        }

        return done
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState) {

        var done by remember { mutableStateOf(false) }
        if (!done) {
            done = setUp(r, c, state)
            return
        }

        val infiniteTransition = rememberInfiniteTransition()
        val rotation by infiniteTransition.animateFloat(
            0f,
            360f,
            animationSpec = infiniteRepeatable(
                animation = tween(4000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(c * 70 + r * 70)
            )
        )

        state.hand1.value = rotation + 45f
        state.hand2.value = state.hand1.value - 180f
    }
}