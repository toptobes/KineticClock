package com.clock.staticpatterns

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.clock.clock.ClockState
import com.clock.clock.Pattern

object Squares : Pattern.Static, Pattern {

    @Composable
    override fun start(r: Int, c: Int, state: ClockState, isRunning: Boolean) {


        val target by remember { mutableStateOf(
            when {
                r % 2 == 0 && c % 2 == 0 -> 90f
                r % 2 == 0 && c % 2 == 1 -> 180f
                r % 2 == 1 && c % 2 == 1 -> 270f
                r % 2 == 1 && c % 2 == 0 -> -0f
                else -> 0f
            }
        ) }

        state.animateTo(target, target + 90f)
    }
}
