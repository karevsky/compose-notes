package com.karevksy.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Gray800,
    primaryVariant = Gray500,
    onPrimary = White,
    secondary = Indigo200,
    secondaryVariant = Indigo500,
    onSecondary = Black,
    error = Red,
    onError = White,
    background = Gray200,
    surface = White
)

@Composable
fun NotesTheme( content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}