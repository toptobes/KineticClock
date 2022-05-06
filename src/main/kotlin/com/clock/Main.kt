import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.clock.App

lateinit var Window: ComposeWindow
    private set

@ExperimentalComposeUiApi
@Preview
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
            App()
        }
    }
}
