package com.karevksy.notes.features.notes.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.karevksy.notes.features.notes.NotesEvent
import com.karevksy.notes.features.notes.NotesViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.notes) {
            NoteItem(
                note = it,
                onDeleteClick = { viewModel.onEvent(NotesEvent.DeleteNote(it)) } ,
                onNoteClick = {}
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    NotesScreen()
}
