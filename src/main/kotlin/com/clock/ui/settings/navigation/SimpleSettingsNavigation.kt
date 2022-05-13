package com.clock.ui.settings.navigation

import androidx.compose.runtime.Composable
import com.clock.ui.settings.Settings
import com.clock.ui.settings.screens.AnimationSpeedSlider

object SimpleSettingsNavigation {
    val screens = listOf<@Composable () -> Unit>(
        { Settings() },
        { AnimationSpeedSlider() }
    )
}