package com.karevksy.core.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karevksy.core.model.dto.Note
import com.karevksy.core.utils.Constants

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = Constants.EMPTY_STRING,
    @ColumnInfo(name = "content") val content: String = Constants.EMPTY_STRING,
    @ColumnInfo(name = "timestamp") val timestamp: Long = 0,
    @ColumnInfo(name = "is_fixed") val isFixed: Boolean = false,
    @ColumnInfo(name = "images") val images: ArrayList<String>? = null
) {
    fun toDto(): Note = Note(
        id = this.id,
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        isFixed = this.isFixed,
        images = this.images
    )
}