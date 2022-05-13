package com.clock.clock

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import com.clock.staticpatterns.*
import com.clock.szttings.Settings.clockSize
import com.clock.szttings.Settings.dm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var currentPattern by mutableStateOf<Pattern>(Rest)

fun startPatternLoop(cs: CoroutineScope) = cs.launch(Dispatchers.Default) {
    while (true) {
        currentPattern = Diamonds
        delay(2000L.dm)
        currentPattern = Octagons
        delay(2000L.dm)
        currentPattern = Squares
        delay(2000L.dm)
        currentPattern = DiagonalWave
        delay(4200L.dm)
        currentPattern = SlowWave
        delay(4200L.dm)
        currentPattern = Time
        delay(5000L.dm)
    }
}

@Composable
fun ClockState.applyCurrentPattern(
    r: Int, c: Int,
    roughPointerPosition: Offset,
    rowStart: Offset = Offset(16f, 16f)
) = this.also {
    val clockCenter = Offset(rowStart.x + c * clockSize, rowStart.y + r * clockSize)
    val pointerPosition = Offset(roughPointerPosition.x - 46, roughPointerPosition.y - 55)

    if (currentPattern is Pattern.Dynamic) {
        (currentPattern as Pattern.Dynamic).start(
            r, c, state = this, clockCenter, pointerPosition
        )
    } else {
        (currentPattern as Pattern.Static).start(
            r, c, state = this
        )
    }
}


