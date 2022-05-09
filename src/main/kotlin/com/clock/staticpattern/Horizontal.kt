package com.clock.staticpattern

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import com.clock.clock.ClockState
import com.clock.clock.Pattern
import kotlinx.coroutines.launch

object Horizontal : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        return true
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState) {

        val rotation1 = remember { Animatable(state.hand1.value) }
        val rotation2 = remember { Animatable(state.hand2.value) }

        val cs = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            cs.launch {
                rotation1.animateTo(0f, tween(1500, easing = LinearEasing))
            }
            rotation2.animateTo(180f, tween(1500, easing = LinearEasing))
        }

        state.hand1.value = rotation1.value
        state.hand2.value = rotation2.value
    }
}
