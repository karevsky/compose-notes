package com.karevksy.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karevksy.domain.model.dto.Note

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "is_fixed") val isFixed: Boolean = false
) {
    fun toDto(): Note = Note(
        id = this.id,
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        isFixed = this.isFixed
    )
}