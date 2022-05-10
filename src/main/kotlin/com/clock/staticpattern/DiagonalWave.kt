package com.clock.staticpattern

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import com.clock.clock.ClockState
import com.clock.clock.Pattern

object DiagonalWave : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        var done by remember { mutableStateOf(false) }
        if (done) return true

        var started by remember { mutableStateOf(true) }

        val angle1 by animateFloatAsState(
            if (started) state.hand1.value - .1f else 0f,
            tween(durationMillis = 500, easing = FastOutSlowInEasing),
            finishedListener = {
                done = true
            }
        )

        val angle2 by animateFloatAsState(
            if (started) state.hand2.value - .1f else 0f,
            tween(durationMillis = 500, easing = FastOutSlowInEasing),
            finishedListener = {
                done = true
            }
        )

        state.hand1.value = angle1
        state.hand2.value = angle2

        started = false
        return false
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

        state.hand1.value = rotation
        state.hand2.value = rotation
    }
}
