package com.karevksy.notes.features.notes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karevksy.core.base.BaseViewModel
import com.karevksy.core.utils.addToDisposable
import com.karevksy.core.utils.androidAsync
import com.karevksy.core.model.dto.Note
import com.karevksy.core.utils.Constants
import com.karevksy.domain.database.useCase.notes.AddNoteUseCase
import com.karevksy.domain.database.useCase.notes.DeleteNoteUseCase
import com.karevksy.domain.database.useCase.notes.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
}

data class NotesState(
    var notes: Map<Boolean, List<Note>> = emptyMap(),
    var toastMessage: String = Constants.EMPTY_STRING,
    var snackMessage: String = Constants.EMPTY_STRING,
    var loading: Boolean = true
)

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : BaseViewModel() {

    private val _uiState = MutableLiveData(NotesState())
    val uiState: LiveData<NotesState> = _uiState

    private var recentlyDeletedNote: Note? = null

    init {
        getNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                deleteNote(event.note)
            }
            is NotesEvent.RestoreNote -> {
                recentlyDeletedNote?.let { addNote(it) }
            }
        }
    }

    private fun getNotes() {
        getNotesUseCase()
            .androidAsync()
            .subscribe { notes ->
                _uiState.value = uiState.value?.copy(
                    notes = notes.map { it.toDto() }
                        .groupBy { it.isFixed },
                    loading = false
                )
            }
            .addToDisposable(compositeDisposable)
    }

    private fun addNote(note: Note) {
        addNoteUseCase(note)
            .androidAsync()
            .subscribe {
                getNotes()
                recentlyDeletedNote = null
            }
            .addToDisposable(compositeDisposable)
    }

    private fun deleteNote(note: Note) {
        deleteNoteUseCase(note)
            .androidAsync()
            .subscribe {
                getNotes()
                recentlyDeletedNote = note
            }
            .addToDisposable(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun addRandNote() {
        addNote(
            Note(
                id = (0..100).random(),
                content = "content",
                title = "title",
                timestamp = 2
            )
        )
    }
}