package com.clock.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clock.Window
import com.clock.szttings.Settings.am

object SettingsText {
    var text by mutableStateOf("")

    @Composable
    fun Text() {
        Box(
            Modifier.offset(
                y = (Window.height / 2 - 30).dp
            )
        ) {
            Text(
                text = SettingsText.text,
                style = TextStyle(
                    color = Color.White.am,
                    fontSize = 18.sp,
                ),
                modifier = Modifier.alpha(.8f)
            )
        }
    }
}