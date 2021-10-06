package com.karevksy.notes.features.notes

import com.karevksy.core.model.dto.Note

data class NotesState(
    var notes: Map<Boolean, List<Note>> = emptyMap(),
    var isLoading: Boolean = false,
    var toastMessage: String = ""
)
