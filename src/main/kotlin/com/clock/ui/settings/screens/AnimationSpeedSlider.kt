package com.clock.ui.settings.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.clock.szttings.Settings
import com.clock.ui.settings.SettingsButton
import com.clock.ui.settings.SettingsIcon
import com.clock.ui.settings.navigation.SettingsScreens

@Composable
fun AnimationSpeedSlider(enabled: MutableState<Boolean>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Slider(
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 270f
                    transformOrigin = TransformOrigin(0f, 0f)
                }
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        Constraints(
                            minWidth = constraints.minHeight,
                            maxWidth = constraints.maxHeight,
                            minHeight = constraints.minWidth,
                            maxHeight = constraints.maxHeight,
                        )
                    )
                    layout(placeable.height, placeable.width) {
                        placeable.place(-placeable.width, 0)
                    }
                }
                .weight(1f)
                .height(50.dp)
                .absolutePadding(right = 16.dp),
            value = Settings.animationSpeedMulti,
            onValueChange = {
                Settings.animationSpeedMulti = it
            },
            valueRange = .25f..2f,
            colors = SliderDefaults.colors(
                thumbColor = Color.White.copy(alpha = .8f),
                activeTrackColor = Color.Black.copy(alpha = .8f),
                inactiveTrackColor = Color.Black.copy(alpha = .6f),
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            )
        )

        SettingsButton({
            SettingsScreens.enabledScreen = "main"
        }) {
            SettingsIcon(painter = rememberVectorPainter(Icons.Default.ArrowBack))
        }
    }
}