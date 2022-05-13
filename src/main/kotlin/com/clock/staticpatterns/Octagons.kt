package com.clock.staticpatterns

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.clock.clock.ClockState
import com.clock.clock.Pattern

object Octagons : Pattern.Static, Pattern {

    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        return true
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState, isRunning: Boolean) {
        if (!isRunning) return

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

        state.animateTo(target, target + 130f)
    }
}