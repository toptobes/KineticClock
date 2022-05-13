package com.clock.clock

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import com.clock.szttings.Settings.asm
import kotlinx.coroutines.launch

data class ClockState(
    val _hand1: MutableState<Float> = mutableStateOf(0f),
    val _hand2: MutableState<Float> = mutableStateOf(0f),
) {
    var hand1 get() = _hand1.value; set(value) { _hand1.value = value }
    var hand2 get() = _hand2.value; set(value) { _hand2.value = value }

    @Composable
    fun animateTo(
        angle1: Float,
        angle2: Float = angle1,
        spec: AnimationSpec<Float> = tween(1500.asm, easing = LinearEasing)
    ) {
        val rotation1 = remember { Animatable(hand1) }
        val rotation2 = remember { Animatable(hand2) }

        val cs = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            cs.launch {
                rotation1.animateTo(angle1, spec)
            }
            rotation2.animateTo(angle2, spec)
        }

        hand1 = rotation1.value
        hand2 = rotation2.value
    }
}