package com.clock.staticpatterns

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import com.clock.clock.ClockState
import com.clock.clock.Pattern
import kotlin.math.abs

object DiagonalWave : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        var done by remember { mutableStateOf(false) }
        if (done) return true

        state.animateTo(0f, spec = tween(1000, easing = LinearEasing))

        if (abs(state.hand1 - 0f) < 0.1f && abs(state.hand2 - 0f) < 0.1f) {
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
                animation = tween(4000/3, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(c * 70 + r * 70)
            )
        )

        state.hand1 = rotation
        state.hand2 = rotation
    }
}