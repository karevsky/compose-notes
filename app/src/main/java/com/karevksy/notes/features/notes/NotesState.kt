package com.karevksy.notes.features.notes

import com.karevksy.domain.model.dto.Note

data class NotesState(
    var notes: List<Note> = emptyList(),
    var isFixedNotesSectionVisible: Boolean = true,
    var isAllNotesSectionVisible: Boolean = true
)
