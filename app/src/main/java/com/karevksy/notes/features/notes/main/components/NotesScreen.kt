package com.karevksy.notes.features.notes.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.karevksy.core.model.dto.Note
import com.karevksy.core.ui.components.DefaultLoader
import com.karevksy.core.ui.components.NotesFloatingActionButton
import com.karevksy.core.utils.observeEvent
import com.karevksy.notes.R
import com.karevksy.notes.features.notes.main.NotesViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val snackScope = rememberCoroutineScope()

    viewModel.navigateTo.observeEvent { screen ->
        navController.navigate(screen.route)
    }

    viewModel.showSnackbar.observeEvent {
        //Хотел обойтись без корутин, но компоуз этого не хочет(((
        snackScope.launch {
            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            scaffoldState.snackbarHostState.showSnackbar(
                message = it,
                actionLabel = "Отмена"
            ).also { result ->
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.onRestoreNoteClick()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            NotesFloatingActionButton(
                icon = Icons.Default.Add,
                action = { viewModel.onAddNoteClick() },
                imageDescription = stringResource(R.string.add_note)
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (viewModel.loading) {
                DefaultLoader(modifier = Modifier.align(Alignment.Center))
            }

            SwipeRefresh(
                modifier = Modifier.fillMaxSize(),
                state = rememberSwipeRefreshState(viewModel.isRefreshing),
                onRefresh = { viewModel.refreshNotes() }
            ) {
                if (viewModel.notes.isEmpty() && !viewModel.loading) {
                    EmptyNotes(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .verticalScroll(rememberScrollState())
                    )
                } else {
                    NotesList(
                        notes = viewModel.notes,
                        onNoteClick = { TODO("Навигация на DetailNoteScreen") },
                        onDeleteClick = { note ->
                            viewModel.onDeleteNoteClick(note.id)
                        }
                    )
                }
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
            text = stringResource(R.string.notes_is_empty),
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = stringResource(R.string.touch_to_add_new_note),
            color = MaterialTheme.colors.secondary
        )
    }
}


