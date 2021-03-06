package com.clock.clock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.clock.szttings.Settings.am
import com.clock.szttings.Settings.clockSize

@Composable
fun Clock(
    state: ClockState,
    clockModifier: Modifier = Modifier
) = Box(
    modifier = clockModifier.clip(CircleShape)
        .requiredSize(clockSize.value.dp)
        .background(color = Color(30, 30, 30, 250).am)
) {
    Needle(state._hand1)
    Needle(state._hand2)
}.also {
    println(clockSize.value)
}

@Composable
private fun Needle(rotation: MutableState<Float>) {
    Canvas(
        Modifier.fillMaxSize().rotate(rotation.value)
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

fun Float.toRad(): Float = (this * (Math.PI / 180f)).toFloat()
fun Float.toDeg(): Float = (this * (180f / Math.PI)).toFloat()