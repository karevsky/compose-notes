package com.karevksy.notes.features.notes.createNote

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
): ViewModel() {
    init {

    }

    override fun onCleared() {
        super.onCleared()
    }
}