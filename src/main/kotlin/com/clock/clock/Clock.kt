package com.clock.clock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import kotlin.math.atan2

@Composable
fun Clock(
    pointerPosition: Offset,
    clockModifier: Modifier = Modifier
) {
    var location by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = clockModifier.clip(CircleShape)
            .background(color = Color(30, 30, 30, 250))
            .onGloballyPositioned {
                location = it.localToWindow(Offset.Zero)
            }
    ) {
        Canvas(
            Modifier.fillMaxSize()
                .rotate(calcAngleFromClockToMouse(pointerPosition, location) - 90f)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                start = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                end = Offset(x = canvasWidth / 2, y = 0f),
                color = Color.White.copy(alpha = 0.8f),
                strokeWidth = 2f
            )
        }
    }
}

fun calcAngleFromClockToMouse(
    clockCenter: Offset,
    mousePosition: Offset
): Float {
    val clockCenterX = clockCenter.x
    val clockCenterY = clockCenter.y
    val mouseX = mousePosition.x + 6
    val mouseY = mousePosition.y + 6

    val angle = atan2(
        y = mouseY - clockCenterY,
        x = mouseX - clockCenterX
    )

    return (angle * 180 / Math.PI).toFloat()
}