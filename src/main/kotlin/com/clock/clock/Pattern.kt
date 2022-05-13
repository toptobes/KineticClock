package com.clock.clock

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset

interface Pattern {
    interface Static {
        @Composable
        fun start(r: Int, c: Int, state: ClockState, isRunning: Boolean)
    }

    interface Dynamic {
        @Composable
        fun start(r: Int, c: Int, state: ClockState, center: Offset, pointerPosition: Offset, isRunning: Boolean)
    }
}
