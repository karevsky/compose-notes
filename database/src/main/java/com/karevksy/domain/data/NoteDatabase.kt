package com.karevksy.domain.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karevksy.core.model.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}