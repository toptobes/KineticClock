package com.clock.ui.settings.navigation

import androidx.compose.runtime.*
import com.clock.ui.settings.screens.AlphaMulti
import com.clock.ui.settings.screens.AnimationDurationSlider
import com.clock.ui.settings.screens.Settings
import com.clock.ui.settings.screens.AnimationSpeedSlider

object SettingsScreens {
    val screens = mapOf<String, @Composable (Boolean) -> Unit>(
        "main" to { Settings(it) },
        "animation_speed" to { AnimationSpeedSlider(it) },
        "animation_duration" to { AnimationDurationSlider(it) },
        "alpha_multi" to { AlphaMulti(it) },
    )

    var enabledScreen by mutableStateOf("main")

    @Composable
    fun DisplayAll() {
        for ((id, screen) in screens) {
            screen(id == enabledScreen)
        }
    }
}
