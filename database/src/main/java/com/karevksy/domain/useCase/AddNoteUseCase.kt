package com.karevksy.domain.useCase

import com.karevksy.core.model.dto.Note
import com.karevksy.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable

interface AddNoteUseCase {
    operator fun invoke(note: Note): Completable
}

class AddNoteUseCaseImpl constructor(
    private val noteRepository: NoteRepository
) : AddNoteUseCase {
    override fun invoke(note: Note): Completable =
        noteRepository.insertNote(note = note.toEntity())
}