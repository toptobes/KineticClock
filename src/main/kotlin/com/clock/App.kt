package com.clock

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.unit.dp
import com.clock.clock.Clock
import com.clock.clock.ClockState
import com.clock.clock.applyCurrentMovement
import com.clock.clock.startMovementLoop

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

    val pointerPosition = remember { mutableStateOf(Offset.Zero) }

    val clocks = rememberClockMatrix()
    val states = rememberClockStateMatrix()

    ClockGrid(pointerPosition) { r, c ->
        clocks[r][c](states[r][c].applyCurrentMovement(
            r, c, pointerPosition.value
        ))
    }
}

@ExperimentalComposeUiApi
@Composable
fun ClockGrid(
    pointerPosition: MutableState<Offset>,
    clock: @Composable (r: Int, c: Int) -> Unit
) {
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
                    pointerPosition.value = it
                    true
                }),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(ROWS) { r ->
                Row {
                    repeat(COLUMNS) { c ->
                        clock(r, c)
                    }
                }
            }
        }
    }
}

@Composable
fun rememberClockMatrix() = remember {
    mutableStateListOf(mutableStateListOf<@Composable (ClockState) -> Unit>()).apply {
        for (i in 0 until 8) {
            add(mutableStateListOf())
            for (j in 0 until 15) {
                get(i).add {
                    Clock(it)
                }
            }
        }
    }
}

@Composable
fun rememberClockStateMatrix() = remember {
    mutableStateListOf(mutableStateListOf<ClockState>()).apply {
        for (i in 0 until 8) {
            add(mutableStateListOf())
            for (j in 0 until 15) {
                get(i).add(
                    ClockState()
                )
            }
        }
    }
}