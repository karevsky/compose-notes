package com.karevksy.domain.database.data

import androidx.room.*
import com.karevksy.core.model.entity.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Single<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id: Int): Single<NoteEntity>

    @Query("SELECT * FROM note WHERE is_fixed = :isFixed")
    fun getNotesByIsFixed(isFixed: Boolean): Single<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity): Completable

    @Delete
    fun deleteNote(note: NoteEntity): Completable

    @Query("DELETE FROM note WHERE id = :id")
    fun deleteNoteById(id: Int): Completable
}