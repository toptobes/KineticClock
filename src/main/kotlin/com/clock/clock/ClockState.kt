package com.clock.clock

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import kotlinx.coroutines.launch

data class ClockState(
    val hand1: MutableState<Float> = mutableStateOf(0f),
    val hand2: MutableState<Float> = mutableStateOf(0f),
) {
    @Composable
    fun animateTo(
        angle1: Float,
        angle2: Float = angle1,
        spec: AnimationSpec<Float> = tween(1500, easing = LinearEasing)
    ) {
        val rotation1 = remember { Animatable(hand1.value) }
        val rotation2 = remember { Animatable(hand2.value) }

        val cs = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            cs.launch {
                rotation1.animateTo(angle1, spec)
            }
            rotation2.animateTo(angle2, spec)
        }

        hand1.value = rotation1.value
        hand2.value = rotation2.value
    }
}