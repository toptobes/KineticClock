package com.clock.clock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun Clock(
    state: ClockState,
    clockModifier: Modifier = Modifier
){
    Box(
        modifier = clockModifier.clip(CircleShape)
            .background(color = Color(30, 30, 30, 250))
    ) {
        Needle(state.hand1)
        Needle(state.hand2)
    }
}

@Composable
private fun Needle(rotation: State<Float>) {
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