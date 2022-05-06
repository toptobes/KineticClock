package com.clock.clock

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import com.clock.dynamicmovements.MouseCircle
import com.clock.staticmovements.Wave
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var currentMovement: Action = Wave

fun startMovementLoop(cs: CoroutineScope) = cs.launch(Dispatchers.Default) {
    while (true) {
        //currentMovement = Wave
        //delay(5000)
        currentMovement = MouseCircle
        delay(5000)
    }
}

@Composable
fun ClockState.applyCurrentMovement(
    r: Int, c: Int,
    roughPointerPosition: Offset,
    rowStart: Offset = Offset(16f, 16f),
    clockSize: Float = 60f
) = this.also {
    val clockCenter = Offset(rowStart.x + c * clockSize, rowStart.y + r * clockSize)
    val pointerPosition = Offset(roughPointerPosition.x, roughPointerPosition.y)

    when (currentMovement) {
        is Action.Dynamic -> {
            (currentMovement as Action.Dynamic).start(
                r, c, state = this, clockCenter, pointerPosition
            )
        }

        is Action.Static -> {
            (currentMovement as Action.Static).start(
                r, c, state = this
            )
        }
    }
}
