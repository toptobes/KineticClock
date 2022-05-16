package com.clock.ui.settings.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.clock.clock.PatternLoop
import com.clock.ui.settings.SettingsButton
import com.clock.ui.settings.SettingsIcon
import com.clock.ui.settings.SettingsText
import com.clock.ui.settings.navigation.SettingsScreens

@Composable
fun Settings(enabled: Boolean) {
    val cs = rememberCoroutineScope()

    val yOffset by animateFloatAsState(
        if (enabled) 0f else 550f,
        tween(durationMillis = 2000)
    )

    if (enabled && yOffset < 50) SettingsText.text = ""

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

        SettingsButton({
            SettingsScreens.enabledScreen = "animation_duration"
        }) {
            SettingsIcon(painter = painterResource("timer.png"))
        }

        SettingsButton({
            SettingsScreens.enabledScreen = "alpha_multi"
        }) {
            SettingsIcon(painter = painterResource("circle-opacity.png"))
        }
    }
}