package com.karevksy.notes.features.notes

import com.karevksy.core.model.dto.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
}