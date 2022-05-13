package com.clock.staticpatterns

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.clock.clock.ClockState
import com.clock.clock.Pattern
import com.clock.szttings.Settings.asm

object Diamonds : Pattern.Static, Pattern {

    @Composable
    override fun start(r: Int, c: Int, state: ClockState, isRunning: Boolean) {
        if (!isRunning) return

        val target by remember { mutableStateOf(
            when {
                r % 2 == 0 && c % 2 == 0 -> 45f
                r % 2 == 0 && c % 2 == 1 -> 135f
                r % 2 == 1 && c % 2 == 1 -> 225f
                r % 2 == 1 && c % 2 == 0 -> -45f
                else -> 0f
            }
        ) }

        state.animateTo(target, target + 180f, tween(1500.asm, easing = LinearEasing))
    }
}
