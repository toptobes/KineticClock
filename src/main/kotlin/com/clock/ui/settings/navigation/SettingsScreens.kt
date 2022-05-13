package com.clock.ui.settings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.clock.ui.settings.screens.Settings
import com.clock.ui.settings.screens.AnimationSpeedSlider

object SettingsScreens {
    val screens = LinkedHashMap<String, @Composable (MutableState<Boolean>) -> Unit>().also {
        it["main"] = { Settings(it) }
        it["animation_speed"] = { AnimationSpeedSlider(it) }
    }

    var enabledScreen = "main"

    @Composable
    fun displayScreens() {
        for ((id, screen) in screens) {
            screen(mutableStateOf(id == enabledScreen))
        }
    }
}