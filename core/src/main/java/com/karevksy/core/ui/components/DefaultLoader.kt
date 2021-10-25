package com.karevksy.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultLoader(modifier: Modifier) {
    CircularProgressIndicator(
        color = MaterialTheme.colors.secondary,
        modifier = modifier.size(50.dp)
    )
}