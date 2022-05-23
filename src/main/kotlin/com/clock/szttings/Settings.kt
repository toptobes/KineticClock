package com.clock.szttings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.clock.Window
import kotlin.math.roundToInt
import kotlin.math.roundToLong

object Settings {
    const val ROWS = 8
    const val COLUMNS = 15

    var animationSpeedMulti by mutableStateOf(1f)
    var durationMulti by mutableStateOf(1f)
    var alphaMulti by mutableStateOf(1f)

    var clockSize = mutableStateOf(0f)
        get() {
            field.value = if (Window.height < Window.width) {
                (Window.height - 32f) / ROWS
            } else {
                (Window.width - 32f) / COLUMNS
            }
            return field
        }

    val Int.asm
        get() = (this * animationSpeedMulti).roundToInt()

    val Long.dm
        get() = (this * durationMulti).roundToLong()

    val Int.dm
        get() = (this * durationMulti).roundToInt()

    val Color.am
        get() = this.copy(alpha = this.alpha * alphaMulti)
}