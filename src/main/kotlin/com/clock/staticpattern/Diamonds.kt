package com.clock.staticpattern

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import com.clock.clock.Pattern
import com.clock.clock.ClockState
import kotlinx.coroutines.launch

object Diamonds : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        return true
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState) {

        val rotation1 = remember { Animatable(state.hand1.value) }
        val rotation2 = remember { Animatable(state.hand2.value) }

        val cs = rememberCoroutineScope()

        val target by remember { mutableStateOf(
            when {
                r % 2 == 0 && c % 2 == 0 -> 45f
                r % 2 == 0 && c % 2 == 1 -> 135f
                r % 2 == 1 && c % 2 == 1 -> 225f
                r % 2 == 1 && c % 2 == 0 -> -45f
                else -> 0f
            }
        ) }

        LaunchedEffect(Unit) {
            cs.launch {
                rotation1.animateTo(target, tween(1500, easing = LinearEasing))
            }
            rotation2.animateTo(target + 180f, tween(1500, easing = LinearEasing))
        }

        state.hand1.value = rotation1.value
        state.hand2.value = rotation2.value
    }
}
