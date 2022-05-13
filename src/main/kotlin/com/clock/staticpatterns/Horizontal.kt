package com.clock.staticpatterns

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.clock.clock.ClockState
import com.clock.clock.Pattern
import com.clock.szttings.Settings.asm

object Horizontal : Pattern.Static, Pattern {

    @Composable
    override fun start(r: Int, c: Int, state: ClockState, isRunning: Boolean) {
        if (!isRunning) return
        state.animateTo(0f, 180f, tween(1500.asm, easing = LinearEasing))
    }
}
