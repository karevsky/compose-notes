package com.karevksy.domain.database.useCase.notes

import com.karevksy.domain.database.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable

interface DeleteNoteByIdUseCase {
    operator fun invoke(id: Int): Completable
}

class DeleteNoteByIdUseCaseImpl(
    val noteRepository: NoteRepository
): DeleteNoteByIdUseCase {
    override fun invoke(id: Int): Completable =
        noteRepository.deleteNoteById(id)
}