package com.clock.clock

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset

interface Movement {
    interface Static {
        fun movement(r: Int, c: Int, state: ClockState)
    }

    interface Dynamic {
        @Composable
        fun movement(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset): ClockState
    }
}