package com.karevksy.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Gray800,
    primaryVariant = Gray500,
    secondary = Indigo200,
    secondaryVariant = Indigo500,
    onPrimary = White,
    onSecondary = Black,
    error = Red,
    onError = White,
    background = White
)

@Composable
fun NotesTheme(darkTheme: Boolean = false,  content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}