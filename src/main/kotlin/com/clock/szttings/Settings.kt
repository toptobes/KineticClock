package com.clock.szttings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt
import kotlin.math.roundToLong

object Settings {
    const val ROWS = 8
    const val COLUMNS = 15

    var clockSizeMulti by mutableStateOf(1f)
    var animationSpeedMulti by mutableStateOf(1f)
    var durationMulti by mutableStateOf(1f)

    val clockSize
        get() = 60f * clockSizeMulti

    val Int.asm
        get() = (this * animationSpeedMulti).roundToInt()

    val Long.dm
        get() = (this * durationMulti).roundToLong()
}