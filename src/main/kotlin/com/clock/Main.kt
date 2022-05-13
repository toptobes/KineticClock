package com.clock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.clock.clock.*
import com.clock.clock.PatternLoop.applyCurrentPattern
import com.clock.szttings.Settings.COLUMNS
import com.clock.szttings.Settings.ROWS
import com.clock.ui.WindowManipulationBox
import com.clock.ui.WindowManipulationBoxState
import com.clock.ui.settings.SettingsBar
import com.clock.ui.settings.SettingsBarState
import com.clock.ui.settings.navigation.SettingsScreens

lateinit var Window: ComposeWindow
    private set

@ExperimentalComposeUiApi
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        transparent = true,
        resizable = false,
        title = "Kinetic Clock",
        icon = painterResource("clock_icon.png"),
        state = rememberWindowState(
            size = DpSize(1000.dp, 600.dp),
        )
    ) {
        Window = window

        val cs = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            PatternLoop.start(cs)
        }

        WindowDraggableArea {
            Box(
                Modifier.clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxSize()
                    .background(color = Color(34, 34, 34, 255))
                    .padding(16.dp)
                    .pointerMoveFilter({
                        WindowManipulationBoxState.isOpen = (it.x > 824 && it.y < 76)
                        SettingsBarState.isOpen = (it.x < 80)
                        false
                    }),
                contentAlignment = Alignment.Center
            ) {
                ClockGrid()
                WindowManipulationBox()
                SettingsBar()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ClockGrid(
    clocks: SnapshotStateList<SnapshotStateList<@Composable (ClockState) -> Unit>> = rememberClockMatrix(),
    states: SnapshotStateList<SnapshotStateList<ClockState>> = rememberClockStateMatrix()
) {
    val pointerPosition = remember { mutableStateOf(Offset.Zero) }

    Column(
        Modifier.fillMaxSize()
            .pointerMoveFilter({
                pointerPosition.value = it; true
            }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(ROWS) { r ->
            Row {
                repeat(COLUMNS) { c ->
                    clocks[r][c](
                        states[r][c].applyCurrentPattern(
                            r, c, pointerPosition.value
                        )
                    )
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