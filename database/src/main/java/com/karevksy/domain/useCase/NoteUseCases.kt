package com.karevksy.domain.useCase

data class NoteUseCases(
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val getNotesUseCase: GetNotesUseCase
)
