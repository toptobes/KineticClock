package com.clock.clock

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class ClockState(
    val hand1: State<Float> = mutableStateOf(0f),
    val hand2: State<Float> = mutableStateOf(0f),
)