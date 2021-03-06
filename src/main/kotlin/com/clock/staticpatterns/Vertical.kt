package com.clock.staticpatterns

import androidx.compose.runtime.Composable
import com.clock.clock.ClockState
import com.clock.clock.Pattern

object Vertical : Pattern.Static, Pattern {

    @Composable
    override fun start(r: Int, c: Int, state: ClockState, isRunning: Boolean) {
        if (!isRunning) return
        state.animateTo(90f, 270f)
    }
}
