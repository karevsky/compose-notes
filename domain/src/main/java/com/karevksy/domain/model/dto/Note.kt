package com.karevksy.domain.model.dto

import com.karevksy.domain.model.entity.NoteEntity

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
    val isFixed: Boolean = false
) {
    fun toEntity(): NoteEntity = NoteEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        isFixed = this.isFixed
    )
}