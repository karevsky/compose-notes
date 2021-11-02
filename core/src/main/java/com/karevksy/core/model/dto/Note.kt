package com.karevksy.core.model.dto

import android.os.Parcelable
import com.karevksy.core.model.entity.NoteEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
    val isFixed: Boolean = false
): Parcelable {
    fun toEntity(): NoteEntity = NoteEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        isFixed = this.isFixed
    )
}