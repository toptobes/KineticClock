package com.clock.ui.settings.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.clock.clock.PatternLoop
import com.clock.ui.settings.SettingsButton
import com.clock.ui.settings.SettingsIcon
import com.clock.ui.settings.navigation.SettingsScreens

@Composable
fun Settings(enabled: MutableState<Boolean>) {
    val cs = rememberCoroutineScope()

    val yOffset by animateFloatAsState(
        if (enabled.value) 0f else 500f,
        tween(durationMillis = 2000)
    )

    println("enabled: ${enabled.value}")
    println("yOffset: $yOffset")

    Column(
        Modifier.fillMaxSize()
            .absoluteOffset(y = yOffset.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
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

        SettingsButton({
            SettingsScreens.enabledScreen = "animation_speed"
        }) {
            SettingsIcon(painter = painterResource("speedometer.png"))
        }

        SettingsButton({}) {
            SettingsIcon(painter = painterResource("timer.png"))
        }

        SettingsButton({}) {
            SettingsIcon(painter = painterResource("circle-opacity.png"))
        }
    }
}