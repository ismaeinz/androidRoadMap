package com.example.grid.responsive

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

data class WindowSize(
    val width: WindowType,
    val height: WindowType,
)

enum class WindowType {
    Compact, Medium, Expanded
}

@Composable
fun rememberWindowSize(modifier: Modifier = Modifier): WindowSize {
    val configuration = LocalConfiguration.current




    return WindowSize(
        width = when {
            configuration.screenWidthDp <= 600 -> WindowType.Compact

            configuration.screenWidthDp <= 840 -> WindowType.Medium
            else -> WindowType.Expanded

        }, height = when {
            configuration.screenHeightDp <= 480 -> WindowType.Compact
            configuration.screenHeightDp <= 900 -> WindowType.Medium
            else -> WindowType.Expanded

        }


    )

}