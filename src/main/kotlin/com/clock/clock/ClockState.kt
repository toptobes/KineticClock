package com.clock.clock

class ClockState(
    hand1: Float = 0f,
    hand2: Float = 0f,
    fused: Boolean = false,
    spread: Boolean = false
) {
    val hand1 = 0f

    val hand2 = 0f
        get() = when {
            fused -> hand1
            spread -> hand1 + 180f
            else -> field
        }

    val fused: Boolean = false

    val spread: Boolean = false

    fun copy(
        hand1: Float = this.hand1,
        hand2: Float = this.hand2,
        fused: Boolean = false,
        spread: Boolean = false
    ) = ClockState(hand1, hand2, fused, spread)
}