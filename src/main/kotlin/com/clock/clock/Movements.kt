package com.clock.clock

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import com.clock.dynamicmovements.MouseCircle

fun SnapshotStateList<SnapshotStateList<ClockState>>.applyMovements(
    rowStart: Offset, pointerPosition: Offset
) {
    for (i in 0 until size) {
        for (j in 0 until get(i).size) {
            val clockCenter = Offset(rowStart.x + j * 100, rowStart.y + i * 100)

            this[i][j] = MouseCircle.movement(
                i, j, this[i][j], clockCenter, pointerPosition
            )
        }
    }
}
