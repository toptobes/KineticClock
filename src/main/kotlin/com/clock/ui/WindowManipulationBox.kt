package com.clock.ui

import com.clock.Window
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.clock.szttings.Settings.am
import kotlin.system.exitProcess

object WindowManipulationBoxState {
    var isOpen by mutableStateOf(false)
}

@Suppress("WrapUnaryOperator")
@Composable
fun BoxScope.WindowManipulationBox() {
    val yOffset by animateDpAsState(
        targetValue = if (WindowManipulationBoxState.isOpen) 0.dp else -100.dp,
        animationSpec = tween(300)
    )

    Box(
        modifier = Modifier
            .size(120.dp, 46.dp)
            .align(Alignment.TopEnd),
        contentAlignment = Alignment.TopEnd,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .offset(-10.dp, yOffset + 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.DarkGray.am),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            var greenButtonAlpha by rememberSaveable { mutableStateOf(140) }

            TitleBarButton(Color(34, 197, 94, greenButtonAlpha).am) {
                Window.isAlwaysOnTop = !Window.isAlwaysOnTop
                greenButtonAlpha = if (Window.isAlwaysOnTop) 200 else 140
            }

            TitleBarButton(Color(251, 191, 36, 140).am) {
                Window.isMinimized = true
            }

            TitleBarButton(Color(190, 18, 60, 140).am) {
                exitProcess(0)
            }
        }
    }
}

@Composable
private fun TitleBarButton(color: Color, onClick: () -> Unit) = Button(
    onClick = {
        onClick()
    },
    colors = ButtonDefaults.buttonColors(
        backgroundColor = color.am,
    ),
    modifier = Modifier.size(30.dp),
    elevation = null,
    shape = CircleShape
) { }
