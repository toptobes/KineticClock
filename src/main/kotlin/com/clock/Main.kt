import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
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
import com.clock.clock.Clock
import com.clock.clock.ClockState
import com.clock.clock.applyCurrentPattern
import com.clock.clock.startPatternLoop

private const val ROWS = 8
private const val COLUMNS = 15

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

        WindowDraggableArea {
            val cs = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                startPatternLoop(cs)
            }

            val pointerPosition = remember { mutableStateOf(Offset.Zero) }

            val clocks = rememberClockMatrix()
            val states = rememberClockStateMatrix()

            ClockGrid(pointerPosition) { r, c ->
                clocks[r][c](
                    states[r][c].applyCurrentPattern(
                        r, c, pointerPosition.value
                    )
                )
            }
        }
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
            .clickable { },
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