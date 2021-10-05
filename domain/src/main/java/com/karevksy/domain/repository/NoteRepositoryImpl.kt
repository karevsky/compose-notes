package com.karevksy.domain.repository

import com.karevksy.domain.data.NoteDao
import com.karevksy.domain.model.entity.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NoteRepository {
    fun getNotes(): Single<List<NoteEntity>>
    fun getNoteById(id: Int): Single<NoteEntity>
    fun insertNote(note: NoteEntity): Completable
    fun deleteNote(note: NoteEntity): Completable
}

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {
    override fun getNotes(): Single<List<NoteEntity>> = dao.getNotes()

    override fun getNoteById(id: Int): Single<NoteEntity> = dao.getNoteById(id)

    override fun insertNote(note: NoteEntity): Completable = dao.insertNote(note)

    override fun deleteNote(note: NoteEntity): Completable = dao.deleteNote(note)
}