package com.clock.clock

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset

interface Action {
    interface Static {
        @Composable
        fun setUp(r: Int, c: Int, state: ClockState): Boolean
        @Composable
        fun start(r: Int, c: Int, state: ClockState)
    }

    interface Dynamic {
        @Composable
        fun setUp(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset): Boolean
        @Composable
        fun start(r: Int, c: Int, state: ClockState, clockCenter: Offset, pointerPosition: Offset)
    }
}
