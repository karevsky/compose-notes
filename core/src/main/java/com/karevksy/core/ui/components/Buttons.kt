package com.karevksy.core.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors()
    ) { ButtonText(title) }
}

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) { ButtonText(title) }
}

@Composable
private fun ButtonText(text: String) {
    Text(text = text.uppercase(Locale.getDefault()))
}