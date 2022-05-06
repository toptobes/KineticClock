package com.clock.clock

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ClockState(
    val hand1: MutableState<Float> = mutableStateOf(0f),
    val hand2: MutableState<Float> = mutableStateOf(0f),
)