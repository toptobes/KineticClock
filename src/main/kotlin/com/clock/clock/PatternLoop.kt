package com.clock.clock

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import com.clock.staticpatterns.*
import com.clock.szttings.Settings.clockSize
import com.clock.szttings.Settings.dm
import kotlinx.coroutines.*

object PatternLoop {
    private var job: Job? = null

    var currentPattern by mutableStateOf<Pattern>(Rest)
        private set

    var isRunning by mutableStateOf(true)
        private set

    fun start(cs: CoroutineScope) {
        isRunning = true

        job = cs.launch(Dispatchers.Default) {
            while (true) {
                currentPattern = Diamonds
                delayAndWaitForUnpause(2000L.dm)
                currentPattern = Octagons
                delayAndWaitForUnpause(2000L.dm)
                currentPattern = Squares
                delayAndWaitForUnpause(2000L.dm)
                currentPattern = DiagonalWave
                delayAndWaitForUnpause(4200L.dm)
                currentPattern = SlowWave
                delayAndWaitForUnpause(4200L.dm)
                currentPattern = Time
                delayAndWaitForUnpause(5000L.dm)
            }
        }
    }

    @Suppress("ControlFlowWithEmptyBody")
    private suspend fun delayAndWaitForUnpause(time: Long) {
        while (!isRunning);
        delay(time)
        while (!isRunning);
    }

    fun stop() {
        isRunning = !isRunning
    }

    fun restart(cs: CoroutineScope) {
        job?.cancel()?.let { start(cs) }
    }

    @Composable
    fun ClockState.applyCurrentPattern(
        r: Int, c: Int,
        roughPointerPosition: Offset,
        rowStart: Offset = Offset(16f, 16f)
    ) = this.also {
        val clockCenter = Offset(rowStart.x + c * clockSize.value, rowStart.y + r * clockSize.value)
        val pointerPosition = Offset(roughPointerPosition.x - 46, roughPointerPosition.y - 55)

        if (currentPattern is Pattern.Dynamic) {
            (currentPattern as Pattern.Dynamic).start(
                r, c, state = this, clockCenter, pointerPosition, isRunning
            )
        } else {
            (currentPattern as Pattern.Static).start(
                r, c, state = this, isRunning
            )
        }
    }
}


