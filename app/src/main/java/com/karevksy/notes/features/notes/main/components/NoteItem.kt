package com.karevksy.notes.features.notes.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.karevksy.core.model.dto.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    cornerRadius: Dp = 16.dp,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 10.dp,
                end = 10.dp,
                start = 10.dp
            )
            .background(
                MaterialTheme.colors.surface,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable(onClick = onNoteClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
                    .padding(end = 32.dp),
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Divider()
            Text(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 10.dp,
                        bottom = 16.dp
                    )
                    .padding(end = 32.dp),
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                maxLines = 7,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}