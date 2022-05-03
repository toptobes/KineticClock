package com.clock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import com.clock.clock.ClockState
import com.clock.clock.Movement
import com.clock.dynamicmovements.MouseCircle
import com.clock.dynamicmovements.SpinAroundMouse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var currentMovement: Movement = MouseCircle

fun startMovementLoop(cs: CoroutineScope) {
    cs.launch {
        while (true) {
            currentMovement = MouseCircle
            delay(5000)

            currentMovement = SpinAroundMouse
            delay(4000)
        }
    }
}

@Composable
fun SnapshotStateList<SnapshotStateList<ClockState>>.applyCurrentMovement(
    rowStart: Offset, roughPointerPosition: Offset, clockRadius: Float
) {
    for (i in 0 until size) {
        for (j in 0 until get(i).size) {
            val clockCenter = Offset(rowStart.x + j * clockRadius, rowStart.y + i * clockRadius)
            val pointerPosition = Offset(roughPointerPosition.x + 15, roughPointerPosition.y + 3)

            this[i][j] = (currentMovement as Movement.Dynamic).movement(
                i, j, this[i][j], clockCenter, pointerPosition
            )
        }
    }
}