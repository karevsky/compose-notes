package com.karevksy.domain.useCase

import com.karevksy.core.model.entity.NoteEntity
import com.karevksy.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Single

interface GetNoteByIdUseCase {
    operator fun invoke(id: Int): Single<NoteEntity>
}

class GetNoteByIdUseCaseImpl constructor(
    private val noteRepository: NoteRepository
) : GetNoteByIdUseCase {
    override fun invoke(id: Int): Single<NoteEntity> =
        noteRepository.getNoteById(id)
}