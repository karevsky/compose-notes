package com.karevksy.notes.features.notes.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karevksy.core.ui.components.NotesFloatingActionButton
import com.karevksy.notes.features.notes.NotesEvent
import com.karevksy.notes.features.notes.NotesViewModel
import com.karevksy.notes.features.util.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            NotesFloatingActionButton(
                icon = Icons.Default.Add,
                action = { navController.navigate(Screens.NotesScreen.route) },
                imageDescription = "Добавить заметку"
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(50.dp)
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                state.notes.forEach { (isFixed, notes) ->
                    stickyHeader {
                        Title(isFixed)
                    }
                    items(notes) { note ->
                        NoteItem(
                            note = note,
                            onNoteClick = {
                                /*TODO*/
                            },
                            onDeleteClick = {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                            }
                        )
                    }
                }
            }
        }
    }
}



