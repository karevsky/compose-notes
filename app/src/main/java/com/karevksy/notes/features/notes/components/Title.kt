package com.karevksy.notes.features.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Title(isFixed: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = if (isFixed) "Закрепленные" else "Все",
            modifier = Modifier
                .padding(start = 10.dp, end = 16.dp, top = 10.dp),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
    }

}