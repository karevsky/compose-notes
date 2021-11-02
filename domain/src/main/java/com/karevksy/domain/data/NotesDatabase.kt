package com.karevksy.domain.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karevksy.core.model.entity.NoteEntity
import com.karevksy.core.model.entity.UserEntity

@Database(
    entities = [NoteEntity::class, UserEntity::class],
    version = 2
)
abstract class NotesDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}