package com.clock.ui.settings

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.clock.ui.settings.navigation.SettingsScreens

object SettingsBarState {
    var isOpen by mutableStateOf(false)
}

@Suppress("WrapUnaryOperator")
@Composable
fun BoxScope.SettingsBar() {
    val xOffset by animateDpAsState(
        targetValue = if (SettingsBarState.isOpen) 0.dp else -100.dp,
        animationSpec = tween(300)
    )

    Box(
        Modifier.fillMaxHeight()
            .absoluteOffset(xOffset)
            .width(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(34, 34, 34, 255))
            .border(BorderStroke(10.dp, Color.DarkGray))
            .align(Alignment.CenterStart),
        Alignment.Center
    ) {
        SettingsScreens.DisplayAll()
    }

    SettingsText.Text()
}

@Composable
fun SettingsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.requiredSize(50.dp),
    enabled: Boolean = true,
    elevation: ButtonElevation? = null,
    shape: Shape = CircleShape,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color.DarkGray,
        contentColor = Color.White.copy(alpha = 0.8f),
    ),
    content: @Composable RowScope.() -> Unit
) = Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    elevation = elevation,
    shape = shape,
    colors = colors,
    content = content
)

@Composable
fun SettingsIcon(
    painter: Painter,
    modifier: Modifier = Modifier.requiredSize(35.dp),
    tint: Color = Color.White
) = Icon(
    painter = painter,
    contentDescription = null,
    tint = tint,
    modifier = modifier
)