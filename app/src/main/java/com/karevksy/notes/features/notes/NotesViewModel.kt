package com.karevksy.notes.features.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.karevksy.core.extensions.addToDisposable
import com.karevksy.core.extensions.androidAsync
import com.karevksy.core.model.dto.Note
import com.karevksy.domain.useCase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    init {
        _state.value.isLoading = true
        addNote(
            Note(
                0,
                title = "Поработать",
                content = "Петпроектик",
                timestamp = 203920,
                isFixed = false
            )
        )
        addNote(
            Note(
                3,
                title = "Покушкать",
                content = "Надо покушкать и заказать еды. Например: " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс, " +
                        "макдональдс",
                timestamp = 203920,
                isFixed = true
            )
        )

        getNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                deleteNote(event.note)
            }
            is NotesEvent.RestoreNote -> {
                addNote(recentlyDeletedNote ?: return)
            }
        }
    }

    private fun getNotes() {
        noteUseCases.getNotesUseCase()
            .androidAsync()
            .subscribe({ notes ->
                _state.value = state.value.copy(
                    notes = notes.map { it.toDto() }.groupBy { it.isFixed }
                )
                _state.value.isLoading = false
            }, {})
            .addToDisposable(disposable)
    }

    private fun addNote(note: Note) {
        noteUseCases.addNoteUseCase(note)
            .androidAsync()
            .subscribe({
                recentlyDeletedNote = null
            }, {})
            .addToDisposable(disposable)
    }

    private fun deleteNote(note: Note) {
        noteUseCases.deleteNoteUseCase(note)
            .androidAsync()
            .subscribe({
                getNotes()
                recentlyDeletedNote = note
            }, {})
            .addToDisposable(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}