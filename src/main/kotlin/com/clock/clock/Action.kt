package com.clock.clock

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset

interface Action {
    interface Static {
        @Composable
        fun start(r: Int, c: Int, state: ClockState)
    }

    interface Dynamic {
        @Composable
        fun start(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset): ClockState
    }
}