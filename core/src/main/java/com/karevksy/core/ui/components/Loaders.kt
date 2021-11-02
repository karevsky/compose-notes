package com.karevksy.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun DefaultLoader(modifier: Modifier) {
    CircularProgressIndicator(
        color = MaterialTheme.colors.secondary,
        modifier = modifier.size(50.dp)
    )
}

@Composable
fun ScreenLoader() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary.copy(alpha = 0.5f)
    ) {
        Box(Modifier.fillMaxSize()) {
            DefaultLoader(modifier = Modifier.align(Alignment.Center))
        }
    }
}