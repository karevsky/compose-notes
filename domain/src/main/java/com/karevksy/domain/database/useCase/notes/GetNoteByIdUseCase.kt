package com.karevksy.domain.database.useCase.notes

import com.karevksy.core.model.dto.Note
import com.karevksy.core.model.entity.NoteEntity
import com.karevksy.domain.database.repository.NoteRepository
import io.reactivex.rxjava3.core.Single

interface GetNoteByIdUseCase {
    operator fun invoke(id: Int): Single<Note>
}

class GetNoteByIdUseCaseImpl constructor(
    private val noteRepository: NoteRepository
) : GetNoteByIdUseCase {
    override fun invoke(id: Int): Single<Note> =
        noteRepository.getNoteById(id).map { it.toDto() }
}