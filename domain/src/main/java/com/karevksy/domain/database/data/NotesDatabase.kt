package com.karevksy.domain.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.karevksy.core.model.entity.NoteEntity
import com.karevksy.core.model.entity.UserEntity
import com.karevksy.domain.database.utils.Converters

@Database(
    entities = [NoteEntity::class, UserEntity::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class NotesDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}