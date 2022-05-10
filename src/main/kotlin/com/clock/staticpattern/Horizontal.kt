package com.clock.staticpattern

import androidx.compose.runtime.Composable
import com.clock.clock.ClockState
import com.clock.clock.Pattern

object Horizontal : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        return true
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState) {
        state.animateTo(0f, 180f)
    }
}
