package com.karevksy.domain.database.useCase.notes

import com.karevksy.core.model.entity.NoteEntity
import com.karevksy.domain.database.repository.NoteRepository
import io.reactivex.rxjava3.core.Single

interface GetNotesUseCase {
    operator fun invoke(): Single<List<NoteEntity>>
}

class GetNotesUseCaseImpl constructor(
    private val noteRepository: NoteRepository
) : GetNotesUseCase {
    override fun invoke(): Single<List<NoteEntity>> =
        noteRepository.getNotes()
}