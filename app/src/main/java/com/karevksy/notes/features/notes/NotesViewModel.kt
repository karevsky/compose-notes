package com.karevksy.notes.features.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karevksy.core.extensions.androidAsync
import com.karevksy.domain.model.dto.Note
import com.karevksy.domain.useCase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private val _uiLiveData = MutableLiveData<String>()
    val uiLiveData: LiveData<String> = _uiLiveData

    private var recentlyDeletedNote: Note? = null

    init {
        disposable.add(
            addNote(Note(
                    0,
                    title = "Title",
                    content = "Content",
                    timestamp = 203920,
                    isFixed = true
                ))
        )
        disposable.add(
            addNote(Note(
                1,
                title = "Title",
                content = "ContentContentContentContentContentContentContentContentContentContentContentContentContentContentContentContentContent",
                timestamp = 203920,
                isFixed = true
            ))
        )
        disposable.add(
            addNote(Note(
                2,
                title = "Title",
                content = "Content",
                timestamp = 203920,
                isFixed = true
            ))
        )
        disposable.add(getNotes())
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                disposable.add(deleteNote(event.note))
            }
            is NotesEvent.RestoreNote -> {
                disposable.add(addNote(recentlyDeletedNote ?: return))
            }
        }
    }

    private fun getNotes(): @NonNull Disposable {
        return noteUseCases.getNotesUseCase()
            .androidAsync()
            .subscribe({ notes ->
                _state.value = state.value.copy(
                    notes = notes.map { it.toDto() }
                )
            }, {
                println(it.localizedMessage)
            })
    }

    private fun addNote(note: Note): @NonNull Disposable {
        return noteUseCases.addNoteUseCase(note)
            .androidAsync()
            .subscribe({
                recentlyDeletedNote = null
            }, {
                println(it.localizedMessage)
            })
    }

    private fun deleteNote(note: Note): @NonNull Disposable {
        return noteUseCases.deleteNoteUseCase(note)
            .androidAsync()
            .subscribe({
                disposable.add(getNotes())
                recentlyDeletedNote = note
            }, {
                println(it.localizedMessage)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}