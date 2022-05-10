package com.clock.staticpattern

import androidx.compose.runtime.*
import com.clock.clock.ClockState
import com.clock.clock.Pattern
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Time : Pattern.Static, Pattern {
    @Composable
    override fun setUp(r: Int, c: Int, state: ClockState): Boolean {
        return true
    }

    @Composable
    override fun start(r: Int, c: Int, state: ClockState) {
        if (clockShouldStandBy(r, c)) {
            Rest.start(-1, -1, state)
            return
        }

        val time by remember { mutableStateOf(getTime()) }

        when {
            c < 4 -> setClockToCorrectPositionForNumber(time[0], r, c, state)
            c < 8 -> setClockToCorrectPositionForNumber(time[1], r, c, state)
            c < 12 -> setClockToCorrectPositionForNumber(time[2], r, c, state)
            c < 16 -> setClockToCorrectPositionForNumber(time[3], r, c, state)
        }
    }

    private fun clockShouldStandBy(r: Int, c: Int) = when {
        r == 0 || r == 7 -> true
        (c + 1) % 4 == 0 -> true
        else -> false
    }

    private fun getTime() = DateTimeFormatter
        .ofPattern("HHmm")
        .withZone(ZoneId.systemDefault())
        .format(Instant.now())

    @Composable
    fun setClockToCorrectPositionForNumber(number: Char, r: Int, c: Int, state: ClockState) {
        when (number) {
            '0' -> ClockNumberPositioner.zero(r, c, state)
            '1' -> ClockNumberPositioner.one(r, c, state)
            '2' -> ClockNumberPositioner.two(r, c, state)
            '3' -> ClockNumberPositioner.three(r, c, state)
            else -> ClockNumberPositioner.zero(r, c, state)
        }
    }

    object ClockNumberPositioner {
        @Composable
        fun zero(r: Int, c: Int, state: ClockState) {
            when {
                r == 1 && (c + 1) % 4 == 1 -> state.animateTo(90f, 180f)
                r == 1 && (c + 1) % 4 == 2 -> state.animateTo(270f, 90f)
                r == 1 && (c + 1) % 4 == 3 -> state.animateTo(180f, 270f)
                r == 6 && (c + 1) % 4 == 1 -> state.animateTo(0f, 90f)
                r == 6 && (c + 1) % 4 == 2 -> state.animateTo(270f, 90f)
                r == 6 && (c + 1) % 4 == 3 -> state.animateTo(270f, 0f)
                r in 2..5 -> Horizontal.start(r, c, state)
            }
        }

        @Composable
        fun one(r: Int, c: Int, state: ClockState) {
            when {
                (c + 1) % 4 == 1 -> Rest.start(r, c, state)
                r in 2..5 -> Horizontal.start(r, c, state)
                r == 1 && (c + 1) % 4 == 2 -> state.animateTo(90f, 180f)
                r == 1 && (c + 1) % 4 == 3 -> state.animateTo(180f, 270f)
                r == 6 && (c + 1) % 4 == 3 -> state.animateTo(270f, 0f)
                r == 6 && (c + 1) % 4 == 2 -> state.animateTo(0f, 90f)
            }
        }

        @Composable
        fun two(r: Int, c: Int, state: ClockState) {
            when {
                r == 1 && (c + 1) % 4 == 1 -> state.animateTo(90f, 180f)
                r == 1 && (c + 1) % 4 == 2 -> state.animateTo(90f, 270f)
                r == 1 && (c + 1) % 4 == 3 -> state.animateTo(180f, 270f)

                r == 2 && (c + 1) % 4 == 1 -> state.animateTo(0f, 90f)
                r == 2 && (c + 1) % 4 == 2 -> state.animateTo(270f, 180f)
                r == 2 && (c + 1) % 4 == 3 -> state.animateTo(0f, 180f)

                r == 3 && (c + 1) % 4 == 1 -> state.animateTo(180f, 90f)
                r == 3 && (c + 1) % 4 == 2 -> state.animateTo(270f, 0f)
                r == 3 && (c + 1) % 4 == 3 -> state.animateTo(0f, 180f)

                r == 4 && (c + 1) % 4 == 1 -> state.animateTo(0f, 180f)
                r == 4 && (c + 1) % 4 == 2 -> state.animateTo(180f, 90f)
                r == 4 && (c + 1) % 4 == 3 -> state.animateTo(0f, 270f)

                r == 5 && (c + 1) % 4 == 1 -> state.animateTo(0f, 180f)
                r == 5 && (c + 1) % 4 == 2 -> state.animateTo(0f, 90f)
                r == 5 && (c + 1) % 4 == 3 -> state.animateTo(270f, 180f)

                r == 6 && (c + 1) % 4 == 1 -> state.animateTo(0f, 90f)
                r == 6 && (c + 1) % 4 == 2 -> state.animateTo(270f, 90f)
                r == 6 && (c + 1) % 4 == 3 -> state.animateTo(0f, 270f)
            }
        }

        @Composable
        fun three(r: Int, c: Int, state: ClockState) {
            when {
                r == 1 && (c + 1) % 4 == 1 -> state.animateTo(90f, 180f)
                r == 1 && (c + 1) % 4 == 2 -> state.animateTo(90f, 270f)
                r == 1 && (c + 1) % 4 == 3 -> state.animateTo(180f, 270f)

                r == 2 && (c + 1) % 4 == 1 -> state.animateTo(0f, 90f)
                r == 2 && (c + 1) % 4 == 2 -> state.animateTo(270f, 180f)
                r == 2 && (c + 1) % 4 == 3 -> state.animateTo(0f, 180f)

                r == 3 && (c + 1) % 4 == 1 -> state.animateTo(90f, 180f)
                r == 3 && (c + 1) % 4 == 2 -> state.animateTo(0f, 270f)
                r == 3 && (c + 1) % 4 == 3 -> state.animateTo(0f, 180f)

                r == 4 && (c + 1) % 4 == 1 -> state.animateTo(0f, 90f)
                r == 4 && (c + 1) % 4 == 2 -> state.animateTo(270f, 180f)
                r == 4 && (c + 1) % 4 == 3 -> state.animateTo(0f, 180f)

                r == 5 && (c + 1) % 4 == 1 -> state.animateTo(180f, 90f)
                r == 5 && (c + 1) % 4 == 2 -> state.animateTo(270f, 0f)
                r == 5 && (c + 1) % 4 == 3 -> state.animateTo(0f, 180f)

                r == 6 && (c + 1) % 4 == 1 -> state.animateTo(0f, 90f)
                r == 6 && (c + 1) % 4 == 2 -> state.animateTo(270f, 90f)
                r == 6 && (c + 1) % 4 == 3 -> state.animateTo(0f, 270f)
            }
        }
    }
}