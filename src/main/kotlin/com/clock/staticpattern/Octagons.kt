package com.clock.staticpattern

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import com.clock.clock.Pattern
import com.clock.clock.ClockState
import kotlinx.coroutines.launch

object Octagons : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        return true
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState) {

        val rotation1 = remember { Animatable(state.hand1.value) }
        val rotation2 = remember { Animatable(state.hand2.value) }

        val cs = rememberCoroutineScope()

        val target by remember {
            mutableStateOf(
                when {
                    r % 2 == 0 && c % 2 == 0 -> 70f
                    r % 2 == 0 && c % 2 == 1 -> 160f
                    r % 2 == 1 && c % 2 == 1 -> 250f
                    r % 2 == 1 && c % 2 == 0 -> -20f
                    else -> 0f
                }
            )
        }

        LaunchedEffect(Unit) {
            cs.launch {
                rotation1.animateTo(target, tween(1500, easing = LinearEasing))
            }
            rotation2.animateTo(target + 130f, tween(1500, easing = LinearEasing))
        }

        state.hand1.value = rotation1.value
        state.hand2.value = rotation2.value
    }
}