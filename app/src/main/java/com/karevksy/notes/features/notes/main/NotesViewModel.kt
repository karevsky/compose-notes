package com.karevksy.notes.features.notes.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karevksy.core.base.BaseViewModel
import com.karevksy.core.model.dto.Note
import com.karevksy.core.utils.*
import com.karevksy.domain.database.useCase.notes.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : BaseViewModel() {

    var isRefreshing by mutableStateOf(false)
    var notes: Map<Boolean, List<Note>> by mutableStateOf(emptyMap())
    var showSnackbar = LiveDataEvent<String>()

    private var recentlyDeletedNote: Note? = null

    init {
        loading = true
        getNotes()
    }

    fun onDeleteNoteClick(id: Int) {
        deleteNote(id)
    }

    fun onRestoreNoteClick() {
        recentlyDeletedNote?.let { addNote(it) }
    }

    private fun getNotes() {
        getNotesUseCase()
            .androidAsync()
            .subscribe { notes ->
                loading = false
                isRefreshing = false
                this.notes = notes.map { it.toDto() }
                    .sortedByDescending { it.timestamp }
                    .groupBy { it.isFixed }
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

    private fun deleteNote(id: Int) {
        getNoteByIdUseCase(id)
            .androidAsync()
            .flatMapCompletable { note ->
                recentlyDeletedNote = note
                deleteNoteByIdUseCase(id)
                    .androidAsync()
            }
            .subscribe {
                getNotes()
                showSnackbar("Метка удалена")
            }
            .addToDisposable(compositeDisposable)
    }

    fun onAddNoteClick() {
        navigateTo.invoke(Screens.CreateNoteScreen)
    }

    fun refreshNotes() {
        isRefreshing = true
        getNotes()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}