package com.clock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp
import com.clock.clock.Clock

private val clockRadius = 50.dp

@ExperimentalComposeUiApi
@Composable
fun App() {
    var pointerPosition by remember { mutableStateOf(Offset.Zero) }

    Box(
        Modifier.clip(shape = RoundedCornerShape(10.dp))
            .fillMaxSize()
            .background(color = Color(34, 34, 34, 255))
            .padding(16.dp)
            .pointerMoveFilter({
                pointerPosition = it
                true
            }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(28) {
                Row(
                    Modifier.height(clockRadius),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(45) {
                        Clock(pointerPosition, Modifier.size(clockRadius))
                    }
                }
                Spacer(Modifier.height(3.dp))
            }
        }
    }
}