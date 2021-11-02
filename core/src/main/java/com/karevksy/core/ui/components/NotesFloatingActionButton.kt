package com.karevksy.core.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.karevksy.core.utils.Constants

@Composable
fun NotesFloatingActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    action: () -> Unit,
    imageDescription: String = Constants.EMPTY_STRING
) {
    FloatingActionButton(
        onClick = action,
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Icon(
            imageVector = icon,
            contentDescription = imageDescription,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}