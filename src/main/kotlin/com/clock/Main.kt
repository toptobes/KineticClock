// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
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

lateinit var WINDOW: ComposeWindow
    private set

@ExperimentalComposeUiApi
@Preview
fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        transparent = true,
        title = "Kinetic Clock",
        icon = painterResource("clock_icon.png"),
        state = rememberWindowState(
            size = DpSize(1600.dp, 900.dp),
        )
    ) {
        WINDOW = window
        WindowDraggableArea {
            App()
        }
    }
}