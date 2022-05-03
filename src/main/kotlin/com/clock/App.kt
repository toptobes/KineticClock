package com.clock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.clock.clock.Clock
import com.clock.clock.ClockState

private const val CLOCK_RADIUS = 50f
private const val ROWS = 8
private const val COLUMNS = 15

@ExperimentalComposeUiApi
@Composable
fun App() {
    val cs = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        startMovementLoop(cs)
    }

    var pointerPosition by remember { mutableStateOf(Offset.Zero) }

    val clockStateMatrix = remember {

        mutableStateListOf(mutableStateListOf<ClockState>()).apply {
            for (i in 0 until 8) {
                add(mutableStateListOf())
                for (j in 0 until 15) {
                    get(i).add(ClockState())
                }
            }
        }
    }

    clockStateMatrix.applyCurrentMovement(
        Offset(98f, 112f),
        pointerPosition,
        CLOCK_RADIUS
    )

    Box(
        Modifier.clip(shape = RoundedCornerShape(10.dp))
            .fillMaxSize()
            .background(color = Color(34, 34, 34, 255))
            .padding(16.dp)
            .clickable {
                println(pointerPosition)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxSize()
                .pointerMoveFilter({
                    pointerPosition = it
                    true
                }),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(ROWS) { i ->
                Row {
                    repeat(COLUMNS) { j ->
                        Clock(clockStateMatrix[i][j], Modifier.requiredSize(CLOCK_RADIUS.dp))
                    }
                }
            }
        }
    }
}
