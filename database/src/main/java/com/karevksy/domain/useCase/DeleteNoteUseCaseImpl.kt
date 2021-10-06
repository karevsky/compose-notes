package com.karevksy.domain.useCase

import com.karevksy.core.model.dto.Note
import com.karevksy.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable

interface DeleteNoteUseCase {
    operator fun invoke(note: Note): Completable
}

class DeleteNoteUseCaseImpl constructor(
    private val noteRepository: NoteRepository
) : DeleteNoteUseCase {
    override fun invoke(note: Note): Completable =
        noteRepository.deleteNote(note = note.toEntity())
}