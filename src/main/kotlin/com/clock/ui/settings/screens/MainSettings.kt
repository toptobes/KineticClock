package com.clock.ui.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.clock.clock.PatternLoop

@Composable
fun Settings() {
    val cs = rememberCoroutineScope()

    SettingsButton({
        PatternLoop.stop()
    }) {
        SettingsIcon(painter = if (PatternLoop.isRunning) {
            painterResource("pause.png")
        } else {
            rememberVectorPainter(Icons.Filled.PlayArrow)
        })
    }

    SettingsButton({
        PatternLoop.restart(cs)
    }) {
        SettingsIcon(painter = rememberVectorPainter(Icons.Default.Refresh))
    }

    SettingsButton({}) {
        SettingsIcon(painter = painterResource("speedometer.png"))
    }

    SettingsButton({}) {
        SettingsIcon(painter = painterResource("timer.png"))
    }

    SettingsButton({}) {
        SettingsIcon(painter = painterResource("circle-opacity.png"))
    }
}