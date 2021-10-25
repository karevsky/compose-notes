package com.karevksy.notes.features.notes.createNote

import androidx.lifecycle.ViewModel
import com.karevksy.domain.useCase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {
    init {

    }

    override fun onCleared() {
        super.onCleared()
    }
}