package com.karevksy.core.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled
    ) { Text(title) }
}