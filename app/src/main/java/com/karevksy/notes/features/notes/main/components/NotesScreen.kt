package com.karevksy.notes.features.notes.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karevksy.core.model.dto.Note
import com.karevksy.core.ui.components.DefaultLoader
import com.karevksy.core.ui.components.NotesFloatingActionButton
import com.karevksy.notes.features.notes.main.NotesEvent
import com.karevksy.notes.features.notes.main.NotesViewModel
import com.karevksy.notes.features.util.Screens
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState.observeAsState().value
    val scaffoldState = rememberScaffoldState()
    val snackScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            NotesFloatingActionButton(
                icon = Icons.Default.Add,
                action = { navController.navigate(Screens.NotesScreen.route) },
                imageDescription = "Добавить заметку"
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (uiState?.loading == true) {
                DefaultLoader(modifier = Modifier.align(Alignment.Center))
            }

            if (uiState?.notes.isNullOrEmpty() && uiState?.loading == false) {
                EmptyNotes(modifier = Modifier.align(Alignment.Center))
            }

            uiState?.notes?.let { notes ->
                NotesList(
                    notes = notes,
                    onNoteClick = { TODO("Навигация на DetailNoteScreen") },
                    onDeleteClick = {
                        viewModel.onEvent(NotesEvent.DeleteNote(it))

                        //Хотел обойтись без корутин, но компоуз этого не хочет(((
                        snackScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Заметка удалена",
                                actionLabel = "Отмена"
                            ).also { result ->
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NotesList(
    modifier: Modifier = Modifier,
    notes: Map<Boolean, List<Note>>,
    onNoteClick: () -> Unit,
    onDeleteClick: (note: Note) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        notes.forEach { (isFixed, notes) ->
            stickyHeader {
                Title(isFixed)
            }
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onNoteClick = onNoteClick,
                    onDeleteClick = {
                        onDeleteClick(note)
                    }
                )
            }
        }
    }
}

@Composable
fun EmptyNotes(modifier: Modifier = Modifier) {
    Column(
        modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Заметки отсутствуют",
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "Нажмите чтобы добавить новую",
            color = MaterialTheme.colors.secondary
        )
    }
}


